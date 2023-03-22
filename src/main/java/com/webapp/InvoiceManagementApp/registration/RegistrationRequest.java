package com.webapp.InvoiceManagementApp.registration;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class RegistrationRequest {
    private final String password;
    private final String email;

/*
    private final Date created_at;
    private final Date updated_at;
*/

    //TODO: maybe nullable or maybe pass nulls here
/*    private final String iban;
    private final String bankName;*/
}
