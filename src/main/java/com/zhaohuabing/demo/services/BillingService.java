package com.zhaohuabing.demo.services;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    public String payment(HttpHeaders headers) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return String.format("Payment accepted\n");
    }
}
