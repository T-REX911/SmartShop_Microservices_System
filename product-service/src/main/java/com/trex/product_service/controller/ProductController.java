package com.trex.product_service.controller;

import com.trex.product_service.Dto.ProductDto;
import com.trex.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    //add
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(ProductDto dto){
        return productService.addProduct(dto);
    }
    //update
    //delete
    //getById
    //getALl
    //getByName
}
