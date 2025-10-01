package com.example.payment_service.controller;

import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.dto.PaymentResponse;
import com.example.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> makePayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.makePayment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPayment(id));
    }
    @GetMapping("/notify/{orderId}")
    public Mono<Void> getPostById(@PathVariable Long orderId, @RequestParam String status) {
        return paymentService.notifyOrderService(orderId,status);
    }

}
