package com.trex.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductInterface {
    @GetMapping("products/id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id);
}
