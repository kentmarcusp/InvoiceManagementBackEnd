package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Long> {
}
