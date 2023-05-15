package com.webapp.InvoiceManagementApp.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.webapp.InvoiceManagementApp.model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.model.ContactType;
import com.webapp.InvoiceManagementApp.model.Invoice;
import com.webapp.InvoiceManagementApp.model.InvoiceRow;
import com.webapp.InvoiceManagementApp.repository.CompanyContactInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class PdfFileService {

    private final InvoiceRowService invoiceRowService;

    private final CompanyContactInfoRepository companyContactInfoRepository;

    @Autowired
    public PdfFileService(InvoiceRowService invoiceRowService, CompanyContactInfoRepository companyContactInfoRepository) {
        this.invoiceRowService = invoiceRowService;
        this.companyContactInfoRepository = companyContactInfoRepository;
    }

    public byte[] generateInvoicePdf(Invoice invoice) throws Exception {

        log.info("Trying to create new invoice printout for invoice with id: " + invoice.getInvoice_id() + " using pdfFileService.");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();


        document.add(getInvoiceNumberTable(invoice));

        document.add(getHeaderContentTable(invoice));
        document.add(Chunk.NEWLINE);

        document.add(getInvoiceReceiverInfoTable());
        document.add(blankSpacerTable(100f));
        getInvoiceBodyFields(document, invoice);
        document.add(blankSpacerTable(50f));
        document.add(getBodyFooter(invoice));

        getInvoiceFooter(document, writer, invoice);

        document.close();
        return outputStream.toByteArray();
    }



    public static PdfPTable getBodyFooter(Invoice invoice) {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);

        ArrayList<String> bodyFooterData = new ArrayList<>();
        bodyFooterData.add(" ");
        bodyFooterData.add(" ");
        bodyFooterData.add(" ");
        bodyFooterData.add("Summa (ilma KM):");
        double priceTotalWithoutVat = invoice.getInvoicePriceSum() - invoice.getVatValue();
        double roundedPrice = roundToTwoDecimals(priceTotalWithoutVat);
        String formattedPrice = String.format("%.2f", roundedPrice);
        bodyFooterData.add(formattedPrice+ "€");

        bodyFooterData.add(" ");
        bodyFooterData.add(" ");
        bodyFooterData.add(" ");
        bodyFooterData.add("Käibemaks:");
        double vatValue = invoice.getVatValue();
        double roundedVatValue = roundToTwoDecimals(vatValue);
        String formattedVatValue = String.format("%.2f€", roundedVatValue);

        bodyFooterData.add(formattedVatValue);

        if (invoice.getSurchargeValue() > 0) {
            bodyFooterData.add(" ");
            bodyFooterData.add(" ");
            bodyFooterData.add(" ");
            bodyFooterData.add("Soodustus:");
            double surchargeValue = invoice.getSurchargeValue();
            double roundedSurchargeValue = roundToTwoDecimals(surchargeValue);
            String formattedSurchargeValue = String.format("%.2f€", roundedSurchargeValue);

            bodyFooterData.add(formattedSurchargeValue);
        }

        bodyFooterData.add(" ");
        bodyFooterData.add(" ");
        bodyFooterData.add(" ");
        bodyFooterData.add("Kokku tasuda:");
        bodyFooterData.add(String.format("%.2f", roundToTwoDecimals(invoice.getInvoicePriceSum()))+"€");



        for (String tile : bodyFooterData) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(3f);
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10f, BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorderWidth(0f);
            cell.setPhrase(new Phrase(tile, headFont));
            table.addCell(cell);
        }

        return table;
    }

    public PdfPTable getInvoiceBodyFields(Document document, Invoice invoice) throws DocumentException {
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100.0f);

        float[] inputColumnWidth = { 4/5f, 5f, 3/2f, 2f, 2f, 5/4f, 2f };
        table.setWidths(inputColumnWidth);

        ArrayList<String> informationRow = new ArrayList<String>();
        informationRow.add("");
        informationRow.add("Toode");
        informationRow.add("Kogus");
        informationRow.add("Hind (KM-ta)");
        informationRow.add("Soodustus");
        informationRow.add("KM");
        informationRow.add("Summa");


        for (String tile : informationRow) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(3f);
            Font textHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10f, BaseColor.BLACK);
            cell.setBackgroundColor(new BaseColor(160, 160, 160));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth(0f);
            cell.setBorderWidthBottom(1/2f);
            cell.setPhrase(new Phrase(tile, textHeaderFont));
            table.addCell(cell);
        }

        ArrayList<String> bodyData = new ArrayList<>();
        List<InvoiceRow> invoiceRowList = fetchInvoiceRowsForInvoiceByInvoiceId(invoice.getInvoice_id());

        for (InvoiceRow row: invoiceRowList) {

            bodyData.add(row.getRowNumber().toString());
            bodyData.add(row.getDescription());
            bodyData.add(row.getRowProductAmount().toString());
            bodyData.add(String.format("%.2f€", roundToTwoDecimals(row.getRowProductPrice())));
            bodyData.add(String.format("%.2f",roundToTwoDecimals(row.getRowSurcharge()))+ "%");
            bodyData.add(String.format("%.2f",roundToTwoDecimals(row.getVatPercentage()))+ "%");
            bodyData.add(String.format("%.2f€",roundToTwoDecimals(row.getTotalSum())));
        }
        return formatTable(bodyData, table, document);
    }

    private List<InvoiceRow> fetchInvoiceRowsForInvoiceByInvoiceId(long invoiceId) {
        return invoiceRowService.getInvoiceRowsByInvoiceId(invoiceId);

    }

    private static PdfPTable formatTable(ArrayList<String> data, PdfPTable table, Document document) throws DocumentException {
        for (String tile : data) {
            PdfPCell cell = new PdfPCell();
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA, 10f, BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setPhrase(new Phrase(tile, headFont));
            formatInvoiceInfoContentTableBorders(cell);
            table.addCell(cell);
        }
        document.add(table);
        return table;
    }

    private static void formatInvoiceInfoContentTableBorders(PdfPCell cell) {
        cell.setBorderWidthLeft(1/2f);
        cell.setBorderWidthRight(1/2f);
        cell.setBorderWidthTop(0f);
        cell.setBorderWidthBottom(1/2f);
    }

    private static PdfPTable blankSpacerTable(float height) {
        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100f);
        PdfPCell cell = new PdfPCell(new Phrase(""));
        cell.setFixedHeight(height);
        cell.setBorderWidth(0f);
        table.addCell(cell);

        return table;
    }

    public CompanyContactInfo getOwnerContactInfoByCustomerIdAndCompanyContactInfoType(Long customerId, Long companyContactInfoTypeId) throws Exception {
        return companyContactInfoRepository.findByCustomerCustomerIdAndContactType_ContactTypeId(customerId, companyContactInfoTypeId)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new Exception("Customer with id: {" + customerId + " or companyContactInfoTypeId: " + companyContactInfoTypeId + "} not found."));
    }

    public void getInvoiceFooter(Document document, PdfWriter writer, Invoice invoice) throws Exception {
        PdfPTable footerTableContent = new PdfPTable(3);
        footerTableContent.setWidthPercentage(100.0f);
        CompanyContactInfo contactInfo = getOwnerContactInfoByCustomerIdAndCompanyContactInfoType(invoice.getCustomer().getCustomerId(), 1L);

        ArrayList<String> footerContactData = new ArrayList<String>();
        footerContactData.add("Arve esitaja");
        footerContactData.add("Telefon: 55555555");
        footerContactData.add("Pank: Swedbank AS");

        footerContactData.add("Aadress: " + contactInfo.getAddress());
        footerContactData.add(contactInfo.getEmail());
        footerContactData.add("IBAN: " + contactInfo.getIban());

        footerContactData.add("Telefon: "+ contactInfo.getPhoneNumber());
        footerContactData.add("");
        footerContactData.add("Pank: " + contactInfo.getBankName());

        footerContactData.add("Reg nr: " + contactInfo.getRegisterCode());
        footerContactData.add(" ");
        footerContactData.add("");

        footerContactData.add("KMKR nr: " + contactInfo.getVatNumber());
        footerContactData.add(" ");
        footerContactData.add(" ");

        for (int i = 0; i < footerContactData.size(); i++) {
            String tile = footerContactData.get(i);
            PdfPCell cell = new PdfPCell();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 10f, BaseColor.BLACK);
            if (i == 0) {
                font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10f, BaseColor.BLACK);
            }
            if (i % 3 == 0) {
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            }
            if (i % 3 == 1) {
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            }
            if (i % 3 == 2) {
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            }
            cell.setBorderWidth(0f);
            cell.setPhrase(new Phrase(tile, font));
            footerTableContent.addCell(cell);
        }

        footerTableContent.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
        footerTableContent.writeSelectedRows(1, -1, document.leftMargin(), document.bottomMargin()+50, writer.getDirectContent());
    }

    public static PdfPTable getHeaderContentTable(Invoice invoice) {
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.getDefaultCell().setBorder(0);
        headerTable.setWidthPercentage(100);
        headerTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPTable headerInnerTable0 = new PdfPTable(2);
        PdfPTable headerInnerTable1 = new PdfPTable(2);
        PdfPTable headerInnerTable2 = new PdfPTable(2);
        PdfPTable headerInnerTable3 = new PdfPTable(2);
        PdfPTable headerInnerTable4 = new PdfPTable(2);


        //Receiver row header
        PdfPCell receiverNameHeader = new PdfPCell(new Phrase("Arve saaja", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        PdfPCell spacer = new PdfPCell();

        //Row 1
        PdfPCell receiverNameText = new PdfPCell(new Phrase(invoice.getCompanyContactInfo().getContactName(), FontFactory.getFont(FontFactory.HELVETICA, 12)));
        receiverNameText.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell referenceNumberText = new PdfPCell(new Phrase("Viitenumber: ", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        referenceNumberText.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell invoiceRefNumberValue = new PdfPCell(new Phrase(invoice.getInvoiceReferenceNumber(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        invoiceRefNumberValue.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerInnerTable0.addCell(referenceNumberText).setBorderWidth(0);
        headerInnerTable0.addCell(invoiceRefNumberValue).setBorderWidth(0);

        //Row 2
        PdfPCell receiverAddressText = new PdfPCell(new Phrase(invoice.getCompanyContactInfo().getAddress(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        receiverAddressText.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell invoiceCreationDate = new PdfPCell(new Phrase("Kuupäev: ", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        invoiceCreationDate.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell invoiceCreationDateResult = new PdfPCell(new Phrase(invoice.getCreated_at(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        invoiceCreationDateResult.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerInnerTable1.addCell(invoiceCreationDate).setBorderWidth(0);
        headerInnerTable1.addCell(invoiceCreationDateResult).setBorderWidth(0);

        //Row 3
        PdfPCell receiverEmail = new PdfPCell(new Phrase(invoice.getCompanyContactInfo().getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        receiverEmail.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell invoicePaymentDate = new PdfPCell(new Phrase("Maksetähtaeg: ", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        invoicePaymentDate.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell invoicePaymentDateResult = new PdfPCell(new Phrase(invoice.getDue_date(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        headerInnerTable2.addCell(invoicePaymentDate).setBorderWidth(0);
        headerInnerTable2.addCell(invoicePaymentDateResult).setBorderWidth(0);

        //Row 4
        PdfPCell receiverPhoneNumber = new PdfPCell(new Phrase(invoice.getCompanyContactInfo().getPhoneNumber(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
        receiverPhoneNumber.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell invoiceSender = new PdfPCell(new Phrase("Arve väljastaja: ", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        invoiceSender.setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell invoiceSenderValue = new PdfPCell(new Phrase("Test esitaja nimi", FontFactory.getFont(FontFactory.HELVETICA, 10)));
        invoicePaymentDate.setHorizontalAlignment(Element.ALIGN_CENTER);
        headerInnerTable3.addCell(invoiceSender).setBorderWidth(0);
        headerInnerTable3.addCell(invoiceSenderValue).setBorderWidth(0);

        headerTable.addCell(receiverNameHeader).setBorderWidth(0);
        headerTable.addCell(spacer).setBorderWidth(0);
        headerTable.addCell(receiverNameText).setBorderWidth(0);
        headerTable.addCell(headerInnerTable0);
        headerTable.addCell(receiverAddressText).setBorderWidth(0);
        headerTable.addCell(headerInnerTable1);
        headerTable.addCell(receiverEmail).setBorderWidth(0);
        headerTable.addCell(headerInnerTable2);
        headerTable.addCell(receiverPhoneNumber).setBorderWidth(0);
        headerTable.addCell(headerInnerTable3);
        headerTable.addCell(spacer).setBorderWidth(0);
        headerTable.addCell(headerInnerTable4);

        return headerTable;
    }

    public static PdfPTable getInvoiceNumberTable(Invoice invoice) {
        PdfPTable invoiceNumberTable = new PdfPTable(1);
        invoiceNumberTable.setWidthPercentage(100);
        invoiceNumberTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell numberTableInput = new PdfPCell(new Phrase("Arve nr: " + invoice.getInvoiceNumber(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));
        numberTableInput.setBorderWidth(0);
        numberTableInput.setHorizontalAlignment(Element.ALIGN_RIGHT);
        invoiceNumberTable.addCell(numberTableInput);

        return invoiceNumberTable;
    }

    public static PdfPTable getInvoiceReceiverInfoTable() {
        PdfPTable receiverTable = new PdfPTable(2);
        receiverTable.setWidthPercentage(40);
        receiverTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        return receiverTable;
    }

    public static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }


}
