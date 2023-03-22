package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.StatusType;
import com.webapp.InvoiceManagementApp.repository.StatusTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusTypeService {

    private final StatusTypeRepository statusTypeRepository;

    @Autowired
    public StatusTypeService(StatusTypeRepository statusTypeRepository) {
        this.statusTypeRepository = statusTypeRepository;
    }

    public List<StatusType> getStatusTypes() {
        return statusTypeRepository.findAll();
    }


}
