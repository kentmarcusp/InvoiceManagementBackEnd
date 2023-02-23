package com.webapp.InvoiceManagementApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contactType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactType_id")
    private long contactType_id;

    @Column(name="name", nullable = false)
    private String status;
}
