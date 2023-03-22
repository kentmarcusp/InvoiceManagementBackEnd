package com.webapp.InvoiceManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name= "invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private long invoice_id;

    @Column(name = "invoiceNumber", nullable = false)
    private Integer invoiceNumber;

    @Column(name = "invoicePriceSum", nullable = false)
    private Double invoicePriceSum;

    @Column(name = "vatValue", nullable = false)
    private Double vatValue;

    @Column(name = "surchargePercentage", nullable = false)
    private Double surchargePercentage;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date due_date;

    @Column(name = "issuerCompanyName", nullable = false)
    private String issuerCompanyName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    //nullable is fine
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="companyContactInfo_id", referencedColumnName = "companyContactInfo_id")
    private CompanyContactInfo companyContactInfo;

    //nullable is fine
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="statusType_id", referencedColumnName = "statusType_id")
    private StatusType statusType;

    //IS NOT NULLABLE
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="invoiceType_id", referencedColumnName = "invoiceType_id", nullable = false)
    private InvoiceType invoiceType;

    /*  @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<InvoiceRow> invoiceRowList; */


}
