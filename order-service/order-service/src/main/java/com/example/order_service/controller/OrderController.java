package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.dto.PaymentRequest;
import com.example.order_service.entity.OrderData;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    // 1️⃣ Create a new order (PENDING)
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // 2️⃣ Pay for an existing order
    @PostMapping("/{orderId}/pay")
    public ResponseEntity<OrderResponse> payOrder(@PathVariable Long orderId,
                                                  @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(orderService.payOrder(orderId, request));
    }

    // 3️⃣ Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderData> getOrder(@PathVariable Long id) {
        return ResponseEntity.of(orderService.getOrderById(id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderData>> getOrders() {
        List<OrderData> orders = orderService.getOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

}
