package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.ContactType;
import com.webapp.InvoiceManagementApp.Service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
