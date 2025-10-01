package com.example.order_service.repository;

import com.example.order_service.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderData,Long> {
}
