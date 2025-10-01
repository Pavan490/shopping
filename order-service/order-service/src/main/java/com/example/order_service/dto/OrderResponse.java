package com.example.order_service.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private String status;
    private Double amount;
    private PaymentResponse payment;
}

