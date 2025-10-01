package com.example.product.dto;

import lombok.Data;

@Data
public class CheckStock {
    private Long productId;
    private Integer quantity;
}
