package com.example.demo2.controller;

import com.example.demo2.bean.Order;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class TestController{
    @Autowired
    public OrderService orderService;






    @GetMapping("/common")
    public String common(){
        return "common";
    }
}