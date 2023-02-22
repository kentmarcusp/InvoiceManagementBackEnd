package com.webapp.InvoiceManagementApp.Repository;

import com.webapp.InvoiceManagementApp.Model.StatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTypeRepository extends JpaRepository<StatusType, Long> {

}
