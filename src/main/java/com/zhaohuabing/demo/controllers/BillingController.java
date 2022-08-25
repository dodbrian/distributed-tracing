package com.zhaohuabing.demo.controllers;

import com.zhaohuabing.demo.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingController {
    @Autowired
    private BillingService billingService;

    @RequestMapping(value = "/payment")
    public String payment(@RequestHeader HttpHeaders headers) {
        return billingService.payment(headers);
    }
}
