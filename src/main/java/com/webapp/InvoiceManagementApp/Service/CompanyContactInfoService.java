package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.Repository.CompanyContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyContactInfoService {

    private final CompanyContactInfoRepository companyContactInfoRepository;

    @Autowired
    public CompanyContactInfoService(CompanyContactInfoRepository companyContactInfoRepository) {
        this.companyContactInfoRepository = companyContactInfoRepository;
    }

    public List<CompanyContactInfo> getCompanyContactInfo() {
        return companyContactInfoRepository.findAll();
    }
}
