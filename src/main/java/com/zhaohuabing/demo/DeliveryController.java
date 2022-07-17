package com.zhaohuabing.demo;

import com.zhaohuabing.demo.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping(value = "/arrangeDelivery")
    public String arrangeDelivery(@RequestHeader HttpHeaders headers) {
        return deliveryService.arrangeDelivery(headers);
    }
}
