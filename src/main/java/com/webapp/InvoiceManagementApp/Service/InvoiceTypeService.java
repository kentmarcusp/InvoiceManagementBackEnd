package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.InvoiceType;
import com.webapp.InvoiceManagementApp.Repository.InvoiceTypeRepository;
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
