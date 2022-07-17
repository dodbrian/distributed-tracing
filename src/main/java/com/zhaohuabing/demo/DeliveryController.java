package com.zhaohuabing.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DeliveryController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/arrangeDelivery")
    public String arrangeDelivery(@RequestHeader HttpHeaders headers) {
        String result = null;
        HttpEntity entity = new HttpEntity("", headers);

        try {
            result = restTemplate.exchange("http://logistics:8080/transport", HttpMethod.GET, entity, String.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
