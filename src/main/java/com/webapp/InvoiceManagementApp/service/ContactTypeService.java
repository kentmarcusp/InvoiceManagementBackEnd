package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.ContactType;
import com.webapp.InvoiceManagementApp.repository.ContactTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    @Autowired
    public ContactTypeService(ContactTypeRepository contactTypeRepository) {
        this.contactTypeRepository = contactTypeRepository;
    }

    public List<ContactType> getContactTypes() {
        return contactTypeRepository.findAll();
    }

    public Optional<ContactType> getContactTypeById(Long id) {
        return contactTypeRepository.findById(id);
    }


}
