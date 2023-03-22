package com.webapp.InvoiceManagementApp.registration;

import com.webapp.InvoiceManagementApp.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationService {

    private final CustomerService customerService;
    private final EmailValidator emailValidator;


    public void checkEmailFormatting(RegistrationRequest request) throws Exception {
        boolean isValidEmailFormat = emailValidator.test(request.getEmail());
        if (!isValidEmailFormat) {
            log.warn("Email format is incorrect!");
            throw new Exception("Email format is incorrect!");
        }
    }

}
