package com.example.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8092", name = "inventories")
public interface InventoryClient {
    @GetMapping("/inventories")
    InventoryStatus exists(@RequestParam("productId") String productId);
}
