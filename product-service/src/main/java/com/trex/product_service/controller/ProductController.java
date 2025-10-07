package com.trex.product_service.controller;

import com.trex.product_service.Dto.ProductDto;
import com.trex.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    //add
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto dto){
        return productService.addProduct(dto);
    }
    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id,@RequestBody ProductDto dto){
        return productService.updateProduct(id,dto);
    }
    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }
    //getById
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id){
        return productService.getProductById(id);
    }
    //getByName
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProductById(@PathVariable String name){
        return productService.getProductByName(name);
    }

    //getALl

}
