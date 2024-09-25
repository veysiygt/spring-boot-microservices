package com.example.shipping.service;

import com.example.shipping.event.OrderPlacedEvent;
import com.example.shipping.model.Shipping;
import com.example.shipping.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ShippingRepository shippingRepository;

    @KafkaListener(topics = "prod.orders.placed", groupId = "shipping-group")
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        Shipping shipping = new Shipping();
        shipping.setOrderId(event.getOrderId());
        this.shippingRepository.save(shipping);

        this.kafkaTemplate.send("prod.orders.shipped", String.valueOf(shipping.getOrderId()), shipping.getOrderId());
    }
}
