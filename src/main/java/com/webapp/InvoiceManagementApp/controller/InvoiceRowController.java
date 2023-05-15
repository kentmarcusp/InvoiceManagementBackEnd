package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.InvoiceRow;
import com.webapp.InvoiceManagementApp.security.JwtTokenUtil;
import com.webapp.InvoiceManagementApp.service.InvoiceRowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/invoicerow")
public class InvoiceRowController {

    private final InvoiceRowService invoiceRowService;
    private final JwtTokenUtil jwtTokenUtil;


    @Autowired
    public InvoiceRowController(InvoiceRowService invoiceRowService, JwtTokenUtil jwtTokenUtil) {
        this.invoiceRowService = invoiceRowService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    @ResponseBody
    public List<InvoiceRow> getAllInvoiceRows() {
        return invoiceRowService.getInvoiceRows();
    }

    @PostMapping("/create")
    public List<InvoiceRow> createListOfInvoiceRows(@RequestBody List<InvoiceRow> invoiceRows,
                                    @RequestHeader(name = "Authorization") String token) throws Exception {
        Long customerId = jwtTokenUtil.getCustomerId(token);
        DecimalFormat df = new DecimalFormat("#.##");

        log.info("Trying to create new invoiceRows for account with id: " + customerId);

        return invoiceRowService.saveAllInvoiceRows(invoiceRows);
    }


}
