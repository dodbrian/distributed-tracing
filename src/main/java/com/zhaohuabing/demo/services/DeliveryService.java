package com.zhaohuabing.demo.services;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeliveryService {
    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    public String arrangeDelivery(HttpHeaders headers) {
        String result = null;
        HttpEntity entity = new HttpEntity("", headers);
        Span span = tracer.buildSpan("arrangeDelivery").start();

        try (Scope scope = tracer.scopeManager().activate(span)) {
            try {
                result = restTemplate.exchange("http://logistics:8080/transport", HttpMethod.GET, entity, String.class).getBody();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            span.log(e.getLocalizedMessage());
        } finally {
            span.finish();
        }

        return result;
    }
}
