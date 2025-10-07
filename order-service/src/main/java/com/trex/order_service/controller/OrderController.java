package com.trex.order_service.controller;

import com.trex.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    //createOrder

    //getOrderById

    //cancelOrder

    //updateOrderStatus

    //updateOrder
}
