package com.example.payment_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long paymentId;
    private Long orderId;
    private Double amount;
    private String method;
    private String status;
    private String transactionId; // added to avoid null

}
