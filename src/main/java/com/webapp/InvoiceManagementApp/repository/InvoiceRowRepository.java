package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.InvoiceRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRowRepository extends JpaRepository<InvoiceRow, Long> {
}
