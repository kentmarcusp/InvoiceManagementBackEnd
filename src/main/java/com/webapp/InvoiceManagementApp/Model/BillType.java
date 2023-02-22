package com.webapp.InvoiceManagementApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "billType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billType_id")
    private long billType_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    //add bill onetomany


}
