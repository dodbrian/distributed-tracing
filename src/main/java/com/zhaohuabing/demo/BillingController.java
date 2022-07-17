package com.zhaohuabing.demo;

import com.zhaohuabing.demo.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {
    @Autowired
    private BillingService billingService;

    @RequestMapping(value = "/payment")
    public void payment() {
        billingService.payment();
    }
}
