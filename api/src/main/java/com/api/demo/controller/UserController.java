package com.api.demo.controller;

import com.api.demo.pojo.User;
import com.api.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
//    @Autowired
//    private RestTemplate restTemplate;
//
    @Autowired
    private IUserService iUserService;
//
//    @RequestMapping("/getName")
//    public User get() throws Exception {
//        //getForObject() 发送一个HTTP GET请求，返回的请求体将映射为一个对象
//        //user-api 为调用的uri（服务提供者（Eureka 客户端）地址）在eureka上注册的application.name
//        return restTemplate.getForObject( "http://api/user/getName", User.class);
//
//    }
    @RequestMapping("/user/get/{uId}")
    public User get(@PathVariable(value = "uId")Integer uId) throws Exception {
        return iUserService.select(uId);
    }

}
