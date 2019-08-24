package com.api.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${env}")
    private String env; // 从配置中心获取

    @RequestMapping("/getConfigInfo")
    public String getConfigInfo() {
        return env;
    }
}
