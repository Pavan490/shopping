package com.example.order_service.service;

import com.example.order_service.client.PaymentClient;
import com.example.order_service.client.ProductClient;
import com.example.order_service.client.UserClient;
import com.example.order_service.dto.*;
import com.example.order_service.entity.OrderData;
import com.example.order_service.repository.OrderRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
@Autowired
    private  OrderRepo orderRepository;
@Autowired
    private  UserClient userClient;
@Autowired
    private  ProductClient productClient;
@Autowired
    private PaymentClient paymentClient;

    // 1️⃣ Create order only (status = PENDING)
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        UserDto user = userClient.getUser(request.getUserId());
        ProductDto product = productClient.getProduct(request.getProductId());

        OrderData order = new OrderData();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setAmount(product.getPrice());
        order.setStatus("PENDING");
        order = orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setStatus(order.getStatus());
        response.setAmount(order.getAmount());
        return response;
    }

    // 2️⃣ Pay for an existing order
    @Transactional
    public OrderResponse payOrder(Long orderId, PaymentRequest request) {
        OrderData order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        PaymentResponse payment = paymentClient.processPayment(
                new PaymentRequest(order.getId(), order.getAmount(), request.getMethod())
        );

        if ("SUCCESS".equals(payment.getStatus()) && payment.getAmount() != null && payment.getAmount() > 0) {
            order.setStatus("PAID");
            order.setAmount(payment.getAmount());
        } else {
            order.setStatus("FAILED");
        }

        orderRepository.save(order);

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setStatus(order.getStatus());
        response.setAmount(order.getAmount());
        response.setPayment(payment);
        return response;
    }

    // 3️⃣ Get order by ID
    public Optional<OrderData> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    //  Get All orders

    public List<OrderData> getOrders() {
        return orderRepository.findAll();
    }
}
