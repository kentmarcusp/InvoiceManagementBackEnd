package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.ContactType;
import com.webapp.InvoiceManagementApp.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/companycontact/contacttype")
public class ContactTypeController {

    private final ContactTypeService contactTypeService;

    @Autowired
    public ContactTypeController(ContactTypeService contactTypeService) {
        this.contactTypeService = contactTypeService;
    }

    @GetMapping
    @ResponseBody
    public List<ContactType> getAllContactTypes(){
        return contactTypeService.getContactTypes();
    }
}
