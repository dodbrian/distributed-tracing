package com.zhaohuabing.demo.controllers;

import com.zhaohuabing.demo.HttpHeaderCarrier;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Huabing Zhao
 */
@RestController
public class EShopController {
    @Autowired
    private Tracer tracer;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        String result = "You have successfully checked out your shopping cart.<br>";

        Span span = tracer.buildSpan("checkout").start();
        tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new HttpHeaderCarrier(headers));

        try {
            HttpEntity entity = new HttpEntity("", headers);
            result += restTemplate.exchange("http://inventory:8080/createOrder", HttpMethod.GET, entity, String.class).getBody();
            result += restTemplate.exchange("http://billing:8080/payment", HttpMethod.GET, entity, String.class).getBody();
            result += restTemplate.exchange("http://delivery:8080/arrangeDelivery", HttpMethod.GET, entity, String.class).getBody();

            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            span.log(e.getLocalizedMessage());
        } finally {
            span.finish();
        }

        return result;
    }
}
