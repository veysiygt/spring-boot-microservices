package com.example.order.controller;

import com.example.order.model.Order;
import com.example.order.request.PlaceOrderRequest;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController{

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequest request) {
        Order createdOrder = orderService.placeOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

}
