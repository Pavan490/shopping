package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_Id")
    private Long id;
    private Long userId;     // comes from User Service
    private Long productId;  // comes from Product Service
    private Double amount;
    private String status;   // CREATED, PAID, FAILED, CANCELLED

    private LocalDateTime orderDate = LocalDateTime.now();
}
