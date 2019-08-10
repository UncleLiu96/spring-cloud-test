package com.provider.demo.controller;

import com.provider.demo.pojo.User;
import com.provider.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("get")
    public User getUser(User user){
        System.out.println(user+"------------"+2);
        List<User> users = iUserService.selectList(user);
        return users.get(0);
    }
}
