package com.zhaohuabing.demo.services;

import com.zhaohuabing.demo.HttpHeaderCarrier;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.propagation.Format;
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
        SpanContext parent = tracer.extract(Format.Builtin.HTTP_HEADERS, new HttpHeaderCarrier(headers));
        Span span = tracer.buildSpan("arrangeDelivery").asChildOf(parent).start();
        String user = span.getBaggageItem("user");
        String result = String.format("Arranging delivery for the user: %s<br>\n", user);

        try (Scope scope = tracer.scopeManager().activate(span)) {
            tracer.inject(span.context(), Format.Builtin.HTTP_HEADERS, new HttpHeaderCarrier(headers));

            try {
                HttpEntity entity = new HttpEntity("", headers);
                result += restTemplate.exchange("http://logistics:8080/transport", HttpMethod.GET, entity, String.class).getBody();
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
