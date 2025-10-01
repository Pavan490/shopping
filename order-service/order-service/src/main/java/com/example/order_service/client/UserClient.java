package com.example.order_service.client;

import com.example.order_service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserClient {
    private final WebClient.Builder webClientBuilder;

    public UserDto getUser(Long userId) {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/auth/" +userId)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}

