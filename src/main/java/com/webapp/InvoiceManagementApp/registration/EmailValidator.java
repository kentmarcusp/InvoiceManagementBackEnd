package com.webapp.InvoiceManagementApp.registration;

import com.webapp.InvoiceManagementApp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {

    private CustomerService customerService;

    @Override
    public boolean test(String email) {
        return isCorrectEmailFormat(email);
    }

    public boolean isCorrectEmailFormat(String email) {
        return email.contains("@");
    }
}
