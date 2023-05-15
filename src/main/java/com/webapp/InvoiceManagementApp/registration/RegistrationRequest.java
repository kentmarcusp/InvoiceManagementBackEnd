package com.webapp.InvoiceManagementApp.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class RegistrationRequest {
    private final String password;
    private final String email;
}
