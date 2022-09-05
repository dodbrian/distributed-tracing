package com.zhaohuabing.demo.services;

import com.zhaohuabing.demo.HttpHeaderCarrier;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private Tracer tracer;

    public String createOrder(HttpHeaders headers) {
        SpanContext parent = tracer.extract(Format.Builtin.HTTP_HEADERS, new HttpHeaderCarrier(headers));
        Span span = tracer.buildSpan("createOrder").asChildOf(parent).start();
        String user = span.getBaggageItem("user");

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span.finish();

        return String.format("Order created by the user: %s<br>\n", user);
    }
}
