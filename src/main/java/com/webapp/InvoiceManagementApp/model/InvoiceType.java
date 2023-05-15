package com.webapp.InvoiceManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "invoiceType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceType_id")
    private long invoiceType_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    public static class InvoiceTypeValue {
        public static String SELL = "INVOICE_SELL";
        public static String PURCHASE = "INVOICE_PURCHASE";
    }

    public static InvoiceType SELL = new InvoiceType(1L, InvoiceTypeValue.SELL, "Müügiarve");
    public static InvoiceType PURCHASE = new InvoiceType(2L, InvoiceTypeValue.PURCHASE, "Ostuarve.");

    public static List<InvoiceType> INVOICETYPES = Arrays.asList(SELL, PURCHASE);



}
