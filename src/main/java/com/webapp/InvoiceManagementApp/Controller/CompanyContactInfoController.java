package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.Service.CompanyContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companycontactinfo")
public class CompanyContactInfoController {

    private final CompanyContactInfoService companyContactInfoService;

    @Autowired
    public CompanyContactInfoController(CompanyContactInfoService companyContactInfoService) {
        this.companyContactInfoService = companyContactInfoService;
    }

    @GetMapping
    @ResponseBody
    public List<CompanyContactInfo> getAllCompanyContactInfo() {
        return companyContactInfoService.getCompanyContactInfo();
    }
}
