package com.api.demo.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.api.demo.feign.ApiFeign;
import com.api.demo.pojo.User;
import com.api.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DefaultProperties(defaultFallback  = "defaultSelect")
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private ApiFeign apiFeign;

    @HystrixCommand
    @Override
    public User select(Integer uId) {
        System.out.println(uId);
        return apiFeign.select(uId);
    }

    public User defaultSelect() {
        User user = new User();
        user.setuId(-1);
        user.setuName("没有数据");
        return user;
    }
}
