package com.turkcell.order_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    @PostMapping()
    public String postMethodName(@RequestBody CreateOrderDto dto) {
        return dto.productId;
    }

    record CreateOrderDto(String productId) {
    }
}
