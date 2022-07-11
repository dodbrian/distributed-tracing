package com.zhaohuabing.demo.services;

import io.opentracing.Scope;
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

    public void arrangeDelivery() {
        Span span = tracer.buildSpan("arrangeDelivery").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            try {
                Thread.sleep(100);
                logisticsService.transport();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            span.log(e.getLocalizedMessage());
        } finally {
            span.finish();
        }
    }
}
