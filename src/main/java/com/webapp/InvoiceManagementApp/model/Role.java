package com.webapp.InvoiceManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long role_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();


    //add role onetomany?

    public static class SpringRole {
        public static String REGULAR_USER = "ROLE_REGULAR_USER";
        public static String ADMIN = "ROLE_ADMIN";
    }

    public static Role REGULAR_USER = new Role(1L, SpringRole.REGULAR_USER, new Date());
    public static Role ADMIN = new Role(2L, SpringRole.ADMIN, new Date());
    public static List<Role> ROLES = Arrays.asList(REGULAR_USER, ADMIN);


    public String toSpringRole() {
        return name;
    }

}

