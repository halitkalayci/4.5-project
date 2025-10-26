package com.turkcell.order_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {
    private final StreamBridge streamBridge;

    public OrdersController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @PostMapping()
    public String createOrder(@RequestBody CreateOrderDto dto) {
        /// .........
        OrderCreatedEvent event = new OrderCreatedEvent(dto.productId());

        Message<OrderCreatedEvent> message = MessageBuilder.withPayload(event).build();

        try {
            boolean isSent = streamBridge.send("orderCreated-out-0", message);
            if (!isSent)
                System.out.println("Mesaj gönderilemedi");
        } catch (Exception e) {
            System.out.println("Mesaj gönderilemedi");
        }

        return dto.productId();
    }

    record CreateOrderDto(String productId) {
    }

    record OrderCreatedEvent(String productId) {
    }
}
