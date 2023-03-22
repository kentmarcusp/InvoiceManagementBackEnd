package com.webapp.InvoiceManagementApp.service;

import com.webapp.InvoiceManagementApp.model.Customer;
import com.webapp.InvoiceManagementApp.model.Role;
import com.webapp.InvoiceManagementApp.repository.CustomerRepository;
import com.webapp.InvoiceManagementApp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findCustomerByEmail(email)
                .map(customer -> new User(
                        email,
                        customer.getPassword(),
                        List.of(new SimpleGrantedAuthority(customer.getRole().getName()))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Email '%s' not found"));
    }


    public Customer saveCustomer(Customer customer) {
        var now = new Date();
        customer.setCreated_at(now);
        customer.setUpdated_at(now);
        customer.setRole(Role.REGULAR_USER);
        return customerRepository.save(customer);

    }

    public boolean checkEmailAvailability(String email) {
        return customerRepository.findCustomerByEmail(email).orElse(null) == null;
    }
}
