package com.webapp.InvoiceManagementApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private long invoice_id;

    @Column(name = "invoiceNumber", nullable = false)
    private String invoiceNumber;

    @Column(name = "invoiceReferenceNumber", nullable = false)
    private String invoiceReferenceNumber;

    @Column(name = "created_at", nullable = false)
    private String created_at;

    @Column(name = "due_date")
    private String due_date;

    @Column(name = "payment_reason")
    private String paymentReason;

    @Column(name = "description")
    private String description;

    @Column(name = "invoicePriceSum", nullable = false)
    private Double invoicePriceSum;

    @Column(name = "vatValue", nullable = false)
    private Double vatValue;

    @Column(name = "surchargeValue", nullable = false)
    private Double surchargeValue;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    //nullable is fine
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyContactInfo_id", referencedColumnName = "companyContactInfo_id")
    private CompanyContactInfo companyContactInfo;

    //nullable is fine
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusType_id", referencedColumnName = "statusType_id")
    private StatusType statusType;

    //IS NOT NULLABLE
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiceType_id", referencedColumnName = "invoiceType_id", nullable = false)
    private InvoiceType invoiceType;

    //@OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<InvoiceRow> invoiceRows;
}
