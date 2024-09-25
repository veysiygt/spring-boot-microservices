package com.example.order.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderPlacedEvent {

    private int orderId;
    private String product;
    private double price;
}
