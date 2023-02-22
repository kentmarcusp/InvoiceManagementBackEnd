package com.webapp.InvoiceManagementApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name= "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    //maybe use sequences?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customer_id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @Column(name = "iban")
    private String iban;

    @Column(name = "bankname")
    private String bankname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;

}
