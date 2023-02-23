package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.InvoiceRow;
import com.webapp.InvoiceManagementApp.Repository.InvoiceRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
