package com.example.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue
    private Long Id;

    private String product;
    private double price;
    private String status;
}
