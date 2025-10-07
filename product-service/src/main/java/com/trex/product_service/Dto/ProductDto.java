package com.trex.product_service.Dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private int qty;
    private double price;
}
