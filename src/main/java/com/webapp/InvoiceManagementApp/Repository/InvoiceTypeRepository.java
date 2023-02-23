package com.webapp.InvoiceManagementApp.Repository;

import com.webapp.InvoiceManagementApp.Model.InvoiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTypeRepository extends JpaRepository<InvoiceType, Long> {
}
