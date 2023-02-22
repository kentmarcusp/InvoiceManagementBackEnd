package com.webapp.InvoiceManagementApp.Repository;

import com.webapp.InvoiceManagementApp.Model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Long = id type
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}


