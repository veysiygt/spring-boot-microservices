package com.example.order.event;

import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "prod.orders.shipped", groupId = "order-group")
    public void handleOrderShippedEvent(String orderId) {
        this.orderRepository.findById(Long.valueOf(orderId)).ifPresent(order -> {
            order.setStatus("SHIPPED");
            this.orderRepository.save(order);
        });
    }
}
