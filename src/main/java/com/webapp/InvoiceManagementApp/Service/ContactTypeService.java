package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.ContactType;
import com.webapp.InvoiceManagementApp.Repository.ContactTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
