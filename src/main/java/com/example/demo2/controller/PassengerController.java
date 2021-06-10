package com.example.demo2.controller;

import com.example.demo2.bean.Passenger;
import com.example.demo2.service.PassengerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @RequestMapping("/book")
    @ResponseBody()
    //判断数据库中是否存在用户所输入乘客信息 不存在：插入信息，返回success；存在：不对数据库做改动
    public String passengerresult(HttpServletRequest request) throws IOException {
        String user_name = request.getParameter("user_name");
        String passenger_id = request.getParameter("passenger_id");
        String user_tel = request.getParameter("user_tel");

        Passenger passenger = new Passenger(user_name, passenger_id, user_tel);
        Passenger result = passengerService.selectAllPassenger(passenger);
        if (result == null) {
            //数据库中不存在此乘客信息，插入
            passengerService.book(passenger);
        }else{
            passengerService.deleteById(passenger);
            passengerService.book(passenger);
        }
        return "success";
    }
}
