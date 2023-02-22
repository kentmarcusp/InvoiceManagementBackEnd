package com.webapp.InvoiceManagementApp.Controller;

import com.webapp.InvoiceManagementApp.Model.Customer;
import com.webapp.InvoiceManagementApp.Service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseBody
    @Transactional
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Customer> getCustomerById(@PathVariable long id) throws Exception {
        Optional<Customer> customer = customerService.findCustomerById(id);
        if (customer.isEmpty()) {
            throw new Exception("Specified user not found with id:." + id);
        }
        return customer;
    }

}
