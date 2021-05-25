package com.example.demo2.controller;


import com.example.demo2.bean.User;
import com.example.demo2.security.MD5Utils;
import com.example.demo2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    public OrderService orderService;

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
