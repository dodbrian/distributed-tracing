package com.zhaohuabing.demo.services;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {
    @Autowired
    private Tracer tracer;
    @Autowired
    private LogisticsService logisticsService;

    public void arrangeDelivery(Span parentSpan) {
        Span span = tracer.buildSpan("arrangeDelivery").asChildOf(parentSpan).start();

        try {
            Thread.sleep(100);
            logisticsService.transport(span);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span.finish();
    }
}
