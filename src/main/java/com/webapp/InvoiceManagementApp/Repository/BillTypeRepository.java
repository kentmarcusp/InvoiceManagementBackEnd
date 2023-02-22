package com.webapp.InvoiceManagementApp.Repository;

import com.webapp.InvoiceManagementApp.Model.BillType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillTypeRepository extends JpaRepository<BillType, Long> {
}
