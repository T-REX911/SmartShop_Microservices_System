package com.trex.order_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trex.order_service.dto.OrderDto;
import com.trex.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    //createOrder
    @PostMapping("/add")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto dto) throws JsonProcessingException {
        return orderService.createOrder(dto);
    }

    //getOrderById

    //cancelOrder

    //updateOrderStatus

    //updateOrder
}
