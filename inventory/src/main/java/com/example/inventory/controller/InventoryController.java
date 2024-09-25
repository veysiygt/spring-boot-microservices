package com.example.inventory.controller;

import com.example.inventory.model.InventoryStatus;
import com.example.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventories")
    public InventoryStatus getInventory(@RequestParam("productId") String productId) {
        return inventoryService.getInventoryStatus(productId);
    }
}
