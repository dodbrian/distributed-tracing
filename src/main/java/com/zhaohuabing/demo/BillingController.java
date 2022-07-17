package com.zhaohuabing.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {
    @RequestMapping(value = "/payment")
    public void payment() {

    }
}
