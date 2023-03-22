package com.webapp.InvoiceManagementApp.registration;

import com.webapp.InvoiceManagementApp.model.Customer;
import com.webapp.InvoiceManagementApp.security.JwtTokenUtil;
import com.webapp.InvoiceManagementApp.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private CustomerService customerService;
    private RegistrationService registrationService;
    private BCryptPasswordEncoder passwordEncoder;

    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) throws Exception {
        log.info("Registering - email: {}, password: {}", request.getEmail(), request.getPassword());

        registrationService.checkEmailFormatting(request);
        if (!customerService.checkEmailAvailability(request.getEmail())) {
            throw new Exception("Email already in use.");
        } else {
            var customer = new Customer();
            customer.setEmail(request.getEmail());
            customer.setPassword(passwordEncoder.encode(request.getPassword()));


            Customer temp = customerService.saveCustomer(customer);

            var token = jwtTokenUtil.generate(temp);
            //log.info("Successful login: " + temp.getEmail());

            return ResponseEntity.ok(token);
        }

    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody RegistrationRequest request) throws Exception {
        log.info("Trying logging in with - email: {}, password: {}", request.getEmail(), request.getPassword());

        var user = customerService.loadUserByUsername(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Bad credentials - invalid username or password.");
            return ResponseEntity.badRequest().build();
        }

        Optional<Customer> customer = customerService.findCustomerByEmail(request.getEmail());

        if (customer.isEmpty()) {
            throw new Exception("Customer not present.");
        }

        var token = jwtTokenUtil.generate(customer.get());
        log.info("Successful login: " + request.getEmail());

        return ResponseEntity.ok(token);
    }

    @GetMapping(path = "/secured")
    public String securedEndpoint() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }
}