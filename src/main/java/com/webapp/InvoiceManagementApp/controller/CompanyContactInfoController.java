package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.security.JwtTokenFilter;
import com.webapp.InvoiceManagementApp.security.JwtTokenUtil;
import com.webapp.InvoiceManagementApp.service.CompanyContactInfoService;
import com.webapp.InvoiceManagementApp.service.ContactTypeService;
import com.webapp.InvoiceManagementApp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/companycontactinfo")
public class CompanyContactInfoController {

    private final CompanyContactInfoService companyContactInfoService;
    private final CustomerService customerService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public CompanyContactInfoController(CompanyContactInfoService companyContactInfoService, JwtTokenUtil jwtTokenUtil, JwtTokenFilter jwtTokenFilter, CustomerService customerService, ContactTypeService contactTypeService) {
        this.companyContactInfoService = companyContactInfoService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerService = customerService;
    }

    @GetMapping()
    @ResponseBody
    public List<CompanyContactInfo> getAllCompanyContactInfo() {
        return companyContactInfoService.getCompanyContactInfo();
    }

    @GetMapping("/all")
    @ResponseBody

    public List<CompanyContactInfo> getAllCompanyContactInfoBasedOnId(@RequestHeader(name = "Authorization") String token) {
        Long customerId = jwtTokenUtil.getCustomerId(token);
        return companyContactInfoService.getCompanyContactInfoByCustomerIdWhereContactTypeIsAdded(customerId);
    }

    @GetMapping("/getcreationinfo")
    @ResponseBody
    public CompanyContactInfo getCompanyCreationInfoBasedOnId(@RequestHeader(name = "Authorization") String token) {
        Long customerId = jwtTokenUtil.getCustomerId(token);
        if (!companyContactInfoService.getCompanyContactInfoByCustomerIdWhereContactTypeIsOwner(customerId).isEmpty()) {
            return companyContactInfoService.getCompanyContactInfoByCustomerIdWhereContactTypeIsOwner(customerId).get(0);
        } else {
            return new CompanyContactInfo();
        }
    }

    @PostMapping()
    public CompanyContactInfo createNewCompanyContact(@RequestBody CompanyContactInfo companyContactInfo,
                                                      @RequestHeader(name = "Authorization") String token) throws Exception {
        log.info("Trying to create new company contact.");

        if (companyContactInfo.getAddress().isEmpty() || companyContactInfo.getContactName().isEmpty()
                || companyContactInfo.getEmail().isEmpty() || companyContactInfo.getRegisterCode().isEmpty()) {

            throw new Exception("missing inputs");
        }
        Long customerId = jwtTokenUtil.getCustomerId(token);
        companyContactInfo.setCustomer(customerService.findCustomerById(customerId).orElseThrow(() ->
                new Exception("Customer with id: {" + customerId + "} not found.")));

        return companyContactInfoService.saveContact(companyContactInfo);

    }

    @DeleteMapping("/{id}")
    public void deleteCompanyContactInfoByCompanyContactInfoId(@RequestHeader(name = "Authorization") String token,
                                                               @PathVariable Long id) throws Exception {
        log.info("Trying to create new company contact.");
        companyContactInfoService.deleteContactInfo(id);
        log.info("ContactInfo deleted successfully");
    }
}
