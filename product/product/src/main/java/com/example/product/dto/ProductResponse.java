package com.example.product.dto;

import lombok.Data;

import java.sql.Blob;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String image;
}
