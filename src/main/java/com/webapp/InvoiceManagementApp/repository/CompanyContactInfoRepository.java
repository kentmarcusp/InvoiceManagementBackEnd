package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.CompanyContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyContactInfoRepository extends JpaRepository<CompanyContactInfo, Long> {

    List<CompanyContactInfo> findByCustomerCustomerId(Long customerId);
    List<CompanyContactInfo> findByCustomerCustomerIdAndContactType_ContactTypeId(Long customerId, Long contactTypeId);
}
