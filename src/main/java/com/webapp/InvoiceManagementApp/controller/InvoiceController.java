package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.Invoice;
import com.webapp.InvoiceManagementApp.security.JwtTokenUtil;
import com.webapp.InvoiceManagementApp.service.CompanyContactInfoService;
import com.webapp.InvoiceManagementApp.service.CustomerService;
import com.webapp.InvoiceManagementApp.service.InvoiceService;
import com.webapp.InvoiceManagementApp.service.PdfFileService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomerService customerService;


    @Autowired
    public InvoiceController(InvoiceService invoiceService, JwtTokenUtil jwtTokenUtil, CustomerService customerService, CompanyContactInfoService companyContactInfoService, CompanyContactInfoService companyContactInfoService1, PdfFileService pdfFileService) {
        this.invoiceService = invoiceService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerService = customerService;
    }

    @Transactional
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Invoice> getInvoiceById(@PathVariable long id) throws Exception {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        if (invoice.isEmpty()) {
            throw new Exception("Specified invoice not found with id: " + id);
        }
        return invoice;
    }

    @Transactional
    @GetMapping("/all")
    @ResponseBody
    public List<Invoice> getAllInvoicesByCustomerId(@RequestHeader(name = "Authorization") String token) {
        Long customerId = jwtTokenUtil.getCustomerId(token);
        return invoiceService.getInvoiceInfoByCustomerId(customerId);
    }
    @Transactional
    @GetMapping("/getallselltypeinvoices")
    @ResponseBody
    public List<Invoice> getAllInvoicesByCustomerIdAndSellType(@RequestHeader(name = "Authorization") String token) {
        Long customerId = jwtTokenUtil.getCustomerId(token);
        return invoiceService.getInvoiceInfoByCustomerIdAndSellType(customerId);
    }

    @PostMapping("/create")
    public Invoice createNewInvoice(@RequestBody Invoice invoiceInfo,
                                    @RequestHeader(name = "Authorization") String token) throws Exception {
        log.info("Trying to create new invoice for account " + token);
        invoiceService.checkIncomingInvoiceFields(invoiceInfo);
        Long customerId = jwtTokenUtil.getCustomerId(token);
        invoiceInfo.setCustomer(customerService.findCustomerById(customerId).orElseThrow(() ->
                new Exception("Customer with id: {" + customerId + "} not found.")));
        return invoiceService.saveInvoiceSellType(invoiceInfo);
    }

    @PutMapping("/confirmstatus")
    public ResponseEntity updateInvoiceStatus(@RequestBody Long invoiceId,
                                              @RequestHeader(name = "Authorization") String token) throws Exception {
        log.info("Trying to update invoice status on invoice with id: " + invoiceId);


        Long customerId = jwtTokenUtil.getCustomerId(token);
        invoiceService.checkInvoiceBelongsToCorrectCustomer(customerId, invoiceId);
        invoiceService.updateInvoiceStatusToConfirmed(invoiceId);

        return ResponseEntity.ok("Invoice status updated successfully");
    }

    @Transactional
    @GetMapping("/printout/{id}")
    @ResponseBody
    public byte[] getInvoicePrintoutById(@PathVariable long id) throws Exception {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        if (invoice.isEmpty()) {
            throw new Exception("Specified invoice not found with id: " + id);
        }
        return invoiceService.generatePdfFile(id);
    }
}
