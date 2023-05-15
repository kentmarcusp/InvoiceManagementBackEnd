package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.InvoiceRow;
import com.webapp.InvoiceManagementApp.repository.InvoiceRowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class InvoiceRowService {

    private final InvoiceRowRepository invoiceRowRepository;

    @Autowired
    public InvoiceRowService(InvoiceRowRepository invoiceRowRepository) {
        this.invoiceRowRepository = invoiceRowRepository;
    }

    public List<InvoiceRow> getInvoiceRows() {
        return invoiceRowRepository.findAll();
    }

    public List<InvoiceRow> saveAllInvoiceRows(List<InvoiceRow> invoiceRowList) {
        log.info("Trying to attach new invoice rows into invoice: ");
        return invoiceRowRepository.saveAll(invoiceRowList);
    }

    public List<InvoiceRow> getInvoiceRowsByInvoiceId(Long invoiceId) {
        return invoiceRowRepository.findInvoiceRowsByInvoiceId(invoiceId);
    }


}
