package com.webapp.InvoiceManagementApp.repository;

import com.webapp.InvoiceManagementApp.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Long = id type
@Repository
@Transactional()
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByEmail(String email);

}


