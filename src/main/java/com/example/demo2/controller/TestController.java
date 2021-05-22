package com.example.demo2.controller;

import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController{
    @Autowired
    public OrderService orderService;


    @GetMapping("/common")
    public String common(){
        return "common";
    }
    @GetMapping("/table")
    public String table(){return "table";
    }
}