package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.InvoiceType;
import com.webapp.InvoiceManagementApp.service.InvoiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoice/invoicetype")
public class InvoiceTypeController {

    private final InvoiceTypeService invoiceTypeService;

    @Autowired
    public InvoiceTypeController(InvoiceTypeService invoiceTypeService){
        this.invoiceTypeService = invoiceTypeService;

    }
    @GetMapping
    @ResponseBody
    public List<InvoiceType> getAllRoles() {
        return invoiceTypeService.getInvoiceTypes();
    }
}
