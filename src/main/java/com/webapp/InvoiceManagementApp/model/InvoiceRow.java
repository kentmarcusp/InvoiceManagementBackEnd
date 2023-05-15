package com.webapp.InvoiceManagementApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "description")
    private String description;

    @Column(name = "rowProductPrice", nullable = false)
    private Double rowProductPrice;

    @Column(name = "rowProductAmount", nullable = false)
    private Integer rowProductAmount;

    @Column(name = "surchargePercentage", nullable = false)
    private Double rowSurcharge;

    @Column(name = "vatPercentage", nullable = false)
    private Double vatPercentage;

    @Column(name = "vatSum", nullable = false)
    private Double vatSum;

    @Column(name = "totalSum", nullable = false)
    private Double totalSum;

/*    @Column(name = "totalSumWithoutVat", nullable = false)
    private Double totalSumWithoutVat;*/

    //IS NOT NULLABLE
   // @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id", referencedColumnName = "invoice_id", nullable = false)
    private Long invoiceId;




}
