package com.webapp.InvoiceManagementApp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "statusType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusType_id")
    private long statusType_id;

    @Column(name = "status")
    private String status;
}
