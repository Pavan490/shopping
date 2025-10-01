package com.example.order_service.client;

import com.example.order_service.dto.PaymentRequest;
import com.example.order_service.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    private final WebClient.Builder webClientBuilder;

    public PaymentResponse processPayment(PaymentRequest request) {
        // synchronous call to Payment Service
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8084/payments/create") // replace with your Payment Service endpoint
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block(); // waits for response
    }
}
