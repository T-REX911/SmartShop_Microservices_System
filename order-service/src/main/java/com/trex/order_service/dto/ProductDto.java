package com.trex.order_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private long id;
    private String name;
    private long qty;
    private double price;
}
