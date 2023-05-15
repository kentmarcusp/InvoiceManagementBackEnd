package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.*;
import com.webapp.InvoiceManagementApp.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final PdfFileService pdfFileService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, PdfFileService pdfFileService) {
        this.invoiceRepository = invoiceRepository;
        this.pdfFileService = pdfFileService;
    }

    @Transactional
    public List<Invoice> getInvoiceInfoByCustomerId(Long id) {
        return invoiceRepository.findByCustomerCustomerId(id);
    }

    @Transactional
    public List<Invoice> getInvoiceInfoByCustomerIdAndSellType(Long id) {
        return invoiceRepository.findByCustomerCustomerIdAndStatusType(id, StatusType.COMPLETED);
    }

    public Optional<Invoice> getInvoiceById(long id) {
        return invoiceRepository.findById(id);
    }

    public void checkIncomingInvoiceFields(Invoice invoice) throws Exception {
        if (invoice.getInvoiceReferenceNumber().isEmpty()) {
            throw new Exception("missing inputs");
        }
    }

    public Invoice saveInvoiceSellType(Invoice invoice) {
        invoice.setInvoiceType(InvoiceType.SELL);
        invoiceRepository.save(invoice);

        return invoice;
    }

    public Invoice updateInvoiceStatusToConfirmed(Long id) throws Exception {
        Invoice updatableInvoice = invoiceRepository.findById(id).orElseThrow(() ->
                new Exception("Invoice with id: {" + id + "} not found."));

        updatableInvoice.setStatusType(StatusType.COMPLETED);
        return invoiceRepository.save(updatableInvoice);
    }

    public void checkInvoiceBelongsToCorrectCustomer(Long customerId, Long invoiceId) throws Exception {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() ->
                new Exception("Invoice with id: {" + invoiceId + "} not found."));

        if (invoice.getCustomer().getCustomerId() != customerId) {
            throw new Exception("Invoice with id: {" + invoiceId + "} does not belong to customer with id " + customerId);
        }
    }

    public boolean checkForUsageOfCompanyContactInfoId(Long companyContactInfoId) {
        List<Invoice> invoiceList = invoiceRepository
                .findByCompanyContactInfoCompanyContactInfoId(companyContactInfoId);
        return !invoiceList.isEmpty();
    }

   public byte[] generatePdfFile(long id) throws Exception {
       Invoice invoice = invoiceRepository.findById(id).orElseThrow(() ->
               new Exception("Invoice with id: {" + id + "} not found."));
       return pdfFileService.generateInvoicePdf(invoice);
   }
}
