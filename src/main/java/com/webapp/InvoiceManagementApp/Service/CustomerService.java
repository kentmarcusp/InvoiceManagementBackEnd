package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.Customer;
import com.webapp.InvoiceManagementApp.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);

    }


}
