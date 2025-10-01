package com.example.payment_service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;      // FK from Order Service
    private Double amount;
    private String method;     // e.g., UPI, CARD, NETBANKING
    private String status;     // SUCCESS, FAILED, PENDING
    private LocalDateTime createdAt;
}
