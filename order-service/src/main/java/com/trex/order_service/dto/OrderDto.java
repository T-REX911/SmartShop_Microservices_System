package com.trex.order_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private String customerName;
    private List<ItemDto> itemDtoList;
}
