package com.webapp.InvoiceManagementApp.Service;

import com.webapp.InvoiceManagementApp.Model.Customer;
import com.webapp.InvoiceManagementApp.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);

    }
    public Optional<Customer> findUserByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email '%s' not found"));
    }

    public Boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
