package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.Invoice;
import com.webapp.InvoiceManagementApp.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(long id) {
        return invoiceRepository.findById(id);
    }
}
