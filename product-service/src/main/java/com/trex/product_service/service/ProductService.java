package com.trex.product_service.service;

import com.trex.product_service.Dto.ProductDto;
import com.trex.product_service.model.Product;
import com.trex.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public ResponseEntity<?> addProduct(ProductDto dto) {
        try{
            Product product = new Product();
            product.setName(dto.getName());
            product.setQty(dto.getQty());
            product.setPrice(dto.getPrice());

            Product save = productRepository.save(product);
            log.info("Product created : "+save.toString());
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("Product not created : "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
