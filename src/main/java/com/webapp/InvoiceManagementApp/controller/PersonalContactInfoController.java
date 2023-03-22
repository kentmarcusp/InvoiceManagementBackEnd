package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.security.JwtTokenUtil;
import com.webapp.InvoiceManagementApp.service.CompanyContactInfoService;
import com.webapp.InvoiceManagementApp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/personalcontactinfo")
public class PersonalContactInfoController {

    private final CompanyContactInfoService companyContactInfoService;
    private final CustomerService customerService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public PersonalContactInfoController(CompanyContactInfoService companyContactInfoService, CustomerService customerService, JwtTokenUtil jwtTokenUtil) {
        this.companyContactInfoService = companyContactInfoService;
        this.customerService = customerService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping()
    public CompanyContactInfo createNewPersonalCompanyContactInfo(@RequestBody CompanyContactInfo companyContactInfo, @RequestHeader(name = "Authorization") String token) throws Exception {
        log.info("Trying to create new personal info.");

        if (companyContactInfo.getContactType().getContactTypeId() != 1) {
            throw new Exception("Unauthorized changes.");
        }

        Long customerId = jwtTokenUtil.getCustomerId(token);
        companyContactInfo.setCustomer(customerService.findCustomerById(customerId).orElseThrow(() ->
                new Exception("Customer with id: {" + customerId + "} not found.")));

        return companyContactInfoService.saveContact(companyContactInfo);

    }

    @PutMapping()
    public ResponseEntity updateCompanyCreationInfo(
            @RequestBody CompanyContactInfo companyContactInfo,
            @RequestHeader(name = "Authorization") String token) throws Exception {
        log.info("Trying to update owner info:");

        if (companyContactInfo.getAddress().isEmpty() || companyContactInfo.getContactName().isEmpty() || companyContactInfo.getEmail().isEmpty() || companyContactInfo.getRegisterCode().isEmpty()) {
            throw new Exception("missing inputs");
        } else {
            Long customerId = jwtTokenUtil.getCustomerId(token);
            Optional<CompanyContactInfo> ownerInformation = companyContactInfoService.getCompanyContactInfoByContactInfoId(companyContactInfo.getCompanyContactInfo_id());
            if (ownerInformation.isEmpty()) {
                throw new Exception("error");
            }
            if (ownerInformation.get().getCustomer().getCustomerId() != customerId) {
                throw new Exception("Unauthorized...");
            }

            companyContactInfoService.saveContact(companyContactInfo);

            return ResponseEntity.ok("Customer updated successfully.");
        }
    }
}
