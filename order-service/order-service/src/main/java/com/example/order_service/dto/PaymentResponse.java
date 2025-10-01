package com.example.order_service.dto;

import lombok.Data;

@Data
public class PaymentResponse {
    private Long paymentId;       // maps to PaymentService.paymentId
    private Long orderId;         // optional, helps debugging
    private Double amount;        // actual paid amount
    private String method;        // payment method used
    private String status;        // SUCCESS, FAILED
    private String transactionId; // generated transaction ID
}
