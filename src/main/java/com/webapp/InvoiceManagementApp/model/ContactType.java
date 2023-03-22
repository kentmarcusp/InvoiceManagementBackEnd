package com.webapp.InvoiceManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "contactType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactTypeId")
    private long contactTypeId;

    @Column(name="name", nullable = false)
    private String name;


/*
    public static class creationContactType {
        public static String addedContact = "addedContact";
        public static String customerOwnerContact = "ownerContact";
    }

    public static ContactType ADDED_CONTACT = new ContactType(1L, creationContactType.addedContact);
    public static ContactType OWNER_CONTACT  = new ContactType(2L, creationContactType.customerOwnerContact);
    public static List<ContactType> ROLES = Arrays.asList(ADDED_CONTACT, OWNER_CONTACT);
*/

}
