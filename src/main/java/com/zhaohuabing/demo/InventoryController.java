package com.zhaohuabing.demo;

import com.zhaohuabing.demo.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/createOrder")
    public void createOrder() {
        inventoryService.createOrder();
    }
}
