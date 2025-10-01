package com.example.product.mapper;

import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public class ProductMapper {

    public static Product dtoToEntity(ProductRequest productResponse){
        Product product=new Product();
        product.setName(productResponse.getName());
        product.setDescription(productResponse.getDescription());
        product.setPrice(productResponse.getPrice());
        product.setStock(productResponse.getStock());
        product.setImage(productResponse.getImage());
        return product;
    }
    public static ProductResponse entityToDto(Product productResponse){
        ProductResponse product=new ProductResponse();
        product.setId(productResponse.getId());
        product.setName(productResponse.getName());
        product.setDescription(productResponse.getDescription());
        product.setPrice(productResponse.getPrice());
        product.setStock(productResponse.getStock());
        product.setImage(productResponse.getImage());
        return product;
    }
}
