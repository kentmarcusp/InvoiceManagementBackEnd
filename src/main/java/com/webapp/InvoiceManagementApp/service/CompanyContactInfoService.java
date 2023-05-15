package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.model.ContactType;
import com.webapp.InvoiceManagementApp.repository.CompanyContactInfoRepository;
import com.webapp.InvoiceManagementApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyContactInfoService {

    private final CompanyContactInfoRepository companyContactInfoRepository;
    private final ContactTypeService contactTypeService;
    private final InvoiceService invoiceService;

    @Autowired
    public CompanyContactInfoService(CompanyContactInfoRepository companyContactInfoRepository, CustomerRepository customerRepository, ContactTypeService contactTypeService, InvoiceService invoiceService) {
        this.companyContactInfoRepository = companyContactInfoRepository;
        this.contactTypeService = contactTypeService;
        this.invoiceService = invoiceService;
    }

    public List<CompanyContactInfo> getCompanyContactInfo() {
        return companyContactInfoRepository.findAll();
    }

    public List<CompanyContactInfo> getCompanyContactInfoByCustomerId(Long id) {
        return companyContactInfoRepository.findByCustomerCustomerId(id);
    }

    public Optional<CompanyContactInfo> getCompanyContactInfoByContactInfoId(Long id) {
        return companyContactInfoRepository.findById(id);
    }

    public CompanyContactInfo saveContact(CompanyContactInfo companyContactInfo) {
        return  companyContactInfoRepository.save(companyContactInfo);
    }


    public List<CompanyContactInfo> getCompanyContactInfoByCustomerIdWhereContactTypeIsAdded(Long id) {
        return companyContactInfoRepository.findByCustomerCustomerIdAndContactType_ContactTypeId(id, 2L);
    }

    public List<CompanyContactInfo> getCompanyContactInfoByCustomerIdWhereContactTypeIsOwner(Long id) {
        ContactType contactTypeOwner = contactTypeService.getContactTypes()
                .stream()
                .filter(contactType -> contactType.getName().equals("Owner"))
                .findFirst()
                .orElse(null);

        return companyContactInfoRepository.findByCustomerCustomerIdAndContactType_ContactTypeId(id, contactTypeOwner.getContactTypeId());
    }

    public void deleteContactInfo(Long id) throws Exception {
        companyContactInfoRepository.delete(getCompanyContactInfoByContactInfoId(id)
                .orElseThrow(() -> new Exception("Contact info with id: " + id + " not found")));
    }

    public boolean isIncludedInExistingInvoices(Long id) {
        return invoiceService.checkForUsageOfCompanyContactInfoId(id);
    }

}
