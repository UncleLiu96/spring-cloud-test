package com.provider.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping(value = "index")
    public String index(){
        System.out.println(123);
        return "index";
    }


}
