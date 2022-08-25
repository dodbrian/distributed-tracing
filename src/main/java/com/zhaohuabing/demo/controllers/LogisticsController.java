package com.zhaohuabing.demo.controllers;

import com.zhaohuabing.demo.services.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {
    @Autowired
    private LogisticsService logisticsService;

    @RequestMapping(value = "/transport")
    public String transport(@RequestHeader HttpHeaders headers) {
        return logisticsService.transport(headers);
    }
}
