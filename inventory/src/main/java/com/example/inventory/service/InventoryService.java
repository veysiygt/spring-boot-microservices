package com.example.inventory.service;

import com.example.inventory.model.InventoryStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryService {

    private final Map<String, InventoryStatus> statuses = Map.of("1", new InventoryStatus(true), "2", new InventoryStatus(false));

    public InventoryStatus getInventoryStatus(String productId) {
        return statuses.getOrDefault(productId, new InventoryStatus(false));
    }
}
