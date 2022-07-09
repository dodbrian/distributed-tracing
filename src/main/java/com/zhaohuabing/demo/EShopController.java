package com.zhaohuabing.demo;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Huabing Zhao
 */
@RestController
public class EShopController {
    @Autowired
    private Tracer tracer;

    @RequestMapping(path = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {

//        JaegerTracer tracer = Configuration.fromEnv("EShop").getTracer();
        Span span = tracer.buildSpan("checkout").start();
        String result = "You have successfully checked out your shopping cart.\n";

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span.finish();

        return result;
    }
}
