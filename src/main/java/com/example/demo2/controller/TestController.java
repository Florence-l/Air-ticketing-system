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

    @RequestMapping("/test11")
    @ResponseBody
    public List<Order> test11(){
        return orderService.findByid(1,10,8);
    }

    @GetMapping("/test22")
    public String test22(){
        return "mine";

    }
}