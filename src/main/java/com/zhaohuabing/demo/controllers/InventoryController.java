package com.zhaohuabing.demo.controllers;

import com.zhaohuabing.demo.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/createOrder")
    public void createOrder(@RequestHeader HttpHeaders headers) {
        inventoryService.createOrder(headers);
    }
}
