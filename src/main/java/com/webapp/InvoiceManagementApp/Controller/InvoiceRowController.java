package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.InvoiceRow;
import com.webapp.InvoiceManagementApp.Service.InvoiceRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoice/invoicerow")
public class InvoiceRowController {

    private final InvoiceRowService invoiceRowService;

    @Autowired
    public InvoiceRowController(InvoiceRowService invoiceRowService) {
        this.invoiceRowService = invoiceRowService;
    }

    @GetMapping
    @ResponseBody
    public List<InvoiceRow> getAllInvoiceRows() {
        return invoiceRowService.getInvoiceRows();
    }
}
