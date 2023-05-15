package com.webapp.InvoiceManagementApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

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

    public static class StatusTypeValue {
        public static String AWAITING = "STATUS_AWAIT";
        public static String COMPLETED = "STATUS_COMPLETED";
    }

    public static StatusType AWAITING = new StatusType(1L, StatusTypeValue.AWAITING);
    public static StatusType COMPLETED = new StatusType(1L, StatusTypeValue.COMPLETED);

    public static List<StatusType> STATUSTYPES = Arrays.asList(AWAITING, COMPLETED);
}
