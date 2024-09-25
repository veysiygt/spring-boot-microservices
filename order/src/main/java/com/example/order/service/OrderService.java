package com.example.order.service;

import com.example.order.client.InventoryStatus;
import com.example.order.event.OrderPlacedEvent;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.request.PlaceOrderRequest;
import com.example.order.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public Order placeOrder(PlaceOrderRequest request) {
        InventoryStatus status = inventoryClient.exists(request.getProduct());
        if (!status.isExists()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in inventory");
        }

        Order order = new Order();
        order.setPrice(request.getPrice());
        order.setProduct(request.getProduct());
        order.setStatus("PLACED");
        Order o = this.orderRepository.save(order);

        this.kafkaTemplate.send("prod.orders.placed", String.valueOf(o.getId()), OrderPlacedEvent.builder()
                .product(request.getProduct())
                .price(request.getPrice())
                .orderId(order.getId().intValue())
                .build());
        return order;
    }
}
