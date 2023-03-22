package com.webapp.InvoiceManagementApp.controller;

import com.webapp.InvoiceManagementApp.model.Customer;
import com.webapp.InvoiceManagementApp.security.JwtTokenUtil;
import com.webapp.InvoiceManagementApp.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtTokenUtil jwtTokenUtil;


    @Autowired
    public CustomerController(CustomerService customerService, JwtTokenUtil jwtTokenUtil) {
        this.customerService = customerService;
        this.jwtTokenUtil = jwtTokenUtil;
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


    @PostMapping(path = "/updateinfo")
    public Customer updateCompanyContacts(@RequestBody Customer customer,
                                          @RequestHeader(name = "Authorization") String token) throws Exception {
        log.info("Trying to update company owner contact info.");

        Long customerId = jwtTokenUtil.getCustomerId(token);

        System.out.println("customer.getCustomerId()");
        System.out.println(customer.getCustomerId());
        System.out.println(customerId);

        return customerService.findCustomerById(customer.getCustomerId()).orElseThrow(() -> new Exception("Customer with id: {" + "test" + "} not found."));
    }

}
