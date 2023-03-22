package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTypeRepository extends JpaRepository<StatusType, Long> {

}
