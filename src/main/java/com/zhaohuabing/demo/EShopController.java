package com.zhaohuabing.demo;

import com.zhaohuabing.demo.services.BillingService;
import com.zhaohuabing.demo.services.DeliveryService;
import com.zhaohuabing.demo.services.InventoryService;
import io.opentracing.Scope;
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

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping(path = "/checkout")
    public String checkout(@RequestHeader HttpHeaders headers) {
        String result = "You have successfully checked out your shopping cart.\n";

        Span span = tracer.buildSpan("checkout").start();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            try {
                inventoryService.createOrder();
                billingService.payment();
                deliveryService.arrangeDelivery();
                Thread.sleep(300);
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
