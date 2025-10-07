package com.trex.product_service.service;

import com.trex.product_service.Dto.ProductDto;
import com.trex.product_service.model.Product;
import com.trex.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public ResponseEntity<?> updateProduct(long id, ProductDto dto) {
        try{
            Optional<Product> currentProduct = productRepository.findById(id);
            if(currentProduct.isPresent()){
                currentProduct.get().setName(dto.getName());
                currentProduct.get().setQty(dto.getQty());
                currentProduct.get().setPrice(dto.getPrice());

                Product save = productRepository.save(currentProduct.get());
                log.info("Product updated : "+save.toString());
                return new ResponseEntity<>(save, HttpStatus.OK);
            }else {
                log.info("Product not found");
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.error("Product not updated : "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    public ResponseEntity<?> deleteProduct(long id) {
        try {
            Optional<Product> currentProduct = productRepository.findById(id);
            if(currentProduct.isPresent()){
                productRepository.deleteById(id);
                log.info("Product deleted : "+ currentProduct.toString());
                return new ResponseEntity<>("Product deleted : "+ currentProduct.toString(), HttpStatus.OK);
            }else {
                log.info("Product not found");
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Product not deleted : "+ e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getProductById(long id) {
        try{
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()){
                return new ResponseEntity<>(product.get(),HttpStatus.OK);
            }else{
                log.info("Product not found");
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error while retrieving data");
            return new ResponseEntity<>("Error while retrieving data : "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
