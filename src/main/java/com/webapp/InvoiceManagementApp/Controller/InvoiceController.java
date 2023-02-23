package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.Customer;
import com.webapp.InvoiceManagementApp.Model.Invoice;
import com.webapp.InvoiceManagementApp.Service.InvoiceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    @ResponseBody
    public List<Invoice> getAllInvoices() {
        return invoiceService.getInvoices();
    }
    @Transactional
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Invoice> getInvoiceById(@PathVariable long id) throws Exception {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        if (invoice.isEmpty()) {
            throw new Exception("Specified user not found with id:." + id);
        }
        return invoice;
    }
}
