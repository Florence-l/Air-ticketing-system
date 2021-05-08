package com.example.demo2.controller;

import com.example.demo2.service.FlightService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
public class FlightInquiryController {
   @Autowired

    @GetMapping("/index")
      public String inquiry(){
       return "flightInquiry"; }

    @GetMapping("/test")
    public String index(){
        return "test";
    }

    @RequestMapping("/flightSchedule")
    public String schedule(){
        return "flightSchedule"; }





}
