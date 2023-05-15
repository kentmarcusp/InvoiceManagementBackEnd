package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.CompanyContactInfo;
import com.webapp.InvoiceManagementApp.model.Invoice;
import com.webapp.InvoiceManagementApp.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByCustomerCustomerId(Long customerId);

    List<Invoice> findByCustomerCustomerIdAndStatusType(Long customerId, StatusType statusType);

    List<Invoice> findByCompanyContactInfoCompanyContactInfoId(Long customerId);

}
