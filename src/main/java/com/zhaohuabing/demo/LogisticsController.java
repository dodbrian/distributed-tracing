package com.zhaohuabing.demo;

import com.zhaohuabing.demo.services.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {
    @Autowired
    private LogisticsService logisticsService;

    @RequestMapping(value = "/transport")
    public void transport() {
        logisticsService.transport();
    }
}
