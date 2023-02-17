package com.zhaohuabing.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryService {
    @Autowired
    private RestTemplate restTemplate;

    public String arrangeDelivery(HttpHeaders headers) {
        String result = String.format("Arranging delivery\n");

        try {
            HttpEntity entity = new HttpEntity("", headers);
            result += restTemplate.exchange("http://logistics:8080/transport", HttpMethod.GET, entity, String.class).getBody();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
