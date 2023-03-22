package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
}
