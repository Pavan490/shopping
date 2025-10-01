package com.example.product.service;

import com.example.product.dto.CheckStock;
import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductResponse createProduct(ProductRequest productDto){
        Product product= ProductMapper.dtoToEntity(productDto);
        Product saved=productRepo.save(product);
        log.info("Product {} is Created",product.getId());
        return ProductMapper.entityToDto(saved);
    }

    public List<ProductResponse> getAllProducts(){

        return productRepo.findAll().stream().map(ProductMapper::entityToDto).collect(Collectors.toList());

    }

    public ProductResponse getProductById(Long id){
        Product product=productRepo.findById(id).orElseThrow(()->new RuntimeException("Product Id Not found"));
        return ProductMapper.entityToDto(product);
    }

    public Product checkStock(CheckStock checkStock){
        Optional<Product> productOptional=productRepo.findById(checkStock.getProductId());
        if(productOptional.isEmpty()){
            throw  new RuntimeException("Product Id is Invalid");
        }
        Product product=productOptional.get();
        if(checkStock.getQuantity()>product.getStock()){
            throw  new RuntimeException("Out Of Stock");
        }
        return product;
    }
}
