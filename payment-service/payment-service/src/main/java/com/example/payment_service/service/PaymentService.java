package com.example.payment_service.service;

import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    // Process payment synchronously
    public PaymentResponse makePayment(PaymentRequest request) {
        // Check if payment already exists for this order
        Payment existing = paymentRepository.findByOrderId(request.getOrderId());
        if (existing != null) {
            return mapToResponse(existing);
        }

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .method(request.getMethod())
                .status("SUCCESS") // Simulate payment success
                .createdAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);
        return mapToResponse(payment);
    }

    // Utility: map entity to DTO
    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .transactionId(generateTransactionId())
                .build();
    }

    // Generate unique transaction ID

    // Optional: notify order service asynchronously (not required for synchronous flow)
    public Mono<Void> notifyOrderService(Long orderId, String status) {
        return webClientBuilder.build()
                .put()
                .uri("http://localhost:8083/orders/" + orderId + "/payment-status") // replace with actual OrderService URL
                .bodyValue(status)
                .retrieve()
                .bodyToMono(Void.class);
    }
    public PaymentResponse getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        return mapToResponse(payment);
    }

    // Utility method to generate unique transaction ID
    private String generateTransactionId() {
        return "TXN-" + System.currentTimeMillis();
    }
}
