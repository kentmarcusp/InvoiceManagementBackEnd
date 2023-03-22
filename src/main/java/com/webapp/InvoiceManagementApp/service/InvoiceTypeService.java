package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.InvoiceType;
import com.webapp.InvoiceManagementApp.repository.InvoiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTypeService {

    private final InvoiceTypeRepository invoiceTypeRepository;

    @Autowired
    public InvoiceTypeService(InvoiceTypeRepository invoiceTypeRepository) {
        this.invoiceTypeRepository = invoiceTypeRepository;
    }

    public List<InvoiceType> getInvoiceTypes() {
        return invoiceTypeRepository.findAll();
    }
}
