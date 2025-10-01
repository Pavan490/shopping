package com.example.product.controller;

import com.example.product.dto.CheckStock;
import com.example.product.dto.ProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
@Autowired
private ProductService productService;
    @PostMapping("/addProduct")
    public ResponseEntity<Map<String,Object>> createProduct( @RequestBody ProductRequest productDto){
        ProductResponse product=productService.createProduct(productDto);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("data",product);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping("/check-stock")
    public ResponseEntity<?> checkStock(@RequestBody CheckStock checkStock){
        Product product=productService.checkStock(checkStock);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("data",product);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Map<String,Object>> getAllProducts(){
        List<ProductResponse> product=productService.getAllProducts();
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("data",product);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> getProductById( @PathVariable Long id){
        ProductResponse product=productService.getProductById(id);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("data",product);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}
