package com.zhaohuabing.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Huabing Zhao
 */
@RestController
public class EShopController {

    @RequestMapping(path = "/checkout")
    public String checkout() {
        return "You have successfully checked out your shopping cart.";
    }
}
