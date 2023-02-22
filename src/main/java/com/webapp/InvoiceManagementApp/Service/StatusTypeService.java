package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.StatusType;
import com.webapp.InvoiceManagementApp.Repository.StatusTypeRepository;
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
