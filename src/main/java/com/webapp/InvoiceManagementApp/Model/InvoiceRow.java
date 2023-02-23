package com.webapp.InvoiceManagementApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "invoiceRow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceRow_id")
    private long invoiceRow_id;

    //autoincrement row number
    @Column(name = "rowNumber", nullable = false)
    private Integer rowNumber;

    @Column(name = "vatPercentage", nullable = false)
    private Double vatPercentage;

    @Column(name = "vatSum", nullable = false)
    private Double vatSum;

    @Column(name = "totalSum", nullable = false)
    private Double totalSum;

    @Column(name = "totalSumWithoutVat", nullable = false)
    private Double totalSumWithoutVat;

    @Column(name = "description", nullable = false)
    private String description;

    //IS NOT NULLABLE
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="invoice_id", referencedColumnName = "invoice_id", nullable = false)
    private Invoice invoice;




}
