package com.webapp.InvoiceManagementApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "companyContactInfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyContactInfo_id")
    private long companyContactInfo_id;

    @Column(name = "contactName", nullable = false)
    private String contactName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "registerCode", nullable = false)
    private String registerCode;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "vatNumber", nullable = false)
    private String vatNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="contactType_id", referencedColumnName = "contactType_id", nullable = false)
    private ContactType contactType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id", referencedColumnName = "customer_id")
    private Customer customer;

}
