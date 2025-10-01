package com.example.order_service.client;


import com.example.order_service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
@RequiredArgsConstructor
public class ProductClient {
    private final WebClient.Builder webClientBuilder;

    public ProductDto getProduct(Long productId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/product/"+productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
}
