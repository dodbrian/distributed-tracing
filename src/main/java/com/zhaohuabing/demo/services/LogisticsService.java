package com.zhaohuabing.demo.services;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticsService {
    @Autowired
    private Tracer tracer;

    public void transport(Span parentSpan) {
        Span span = tracer.buildSpan("transport").asChildOf(parentSpan).start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span.finish();
    }
}
