package com.example.demo2.controller;

import com.example.demo2.bean.Passenger;
import com.example.demo2.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;


    @PostMapping("/book")
    public String book(String user_name, Integer passenger_id, Integer user_tel){
        //创建一个Passenger对象
        Passenger passenger = new Passenger();
        passenger.setUser_name(user_name);
        passenger.setPassenger_id(passenger_id);
        passenger.setUser_tel(user_tel);

        //保存乘客信息,将用户填写的乘客信息写入到数据库中
        Passenger p = new Passenger(user_name, passenger_id, user_tel);
        passengerService.book(p);
        //跳转到支付页面
        return "redirect:/payment";
    }
}
