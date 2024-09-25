package com.example.shipping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Shipping {

    @Id
    @GeneratedValue
    private Long Id;

    private int orderId;
}
