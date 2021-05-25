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
    //判断数据库中是否存在用户所输入乘客信息 不存在：插入信息，返回1 存在：返回0
    public String passengerresult(HttpServletRequest request) throws IOException {
        String user_name = request.getParameter("user_name");
        Integer passenger_id = Integer.parseInt(request.getParameter("passenger_id"));
        Integer user_tel = Integer.parseInt(request.getParameter("user_tel"));

        Passenger passenger = new Passenger(user_name, passenger_id, user_tel);
        Passenger result = passengerService.selectAllPassenger(passenger);
        if(result == null){
            //数据库中不存在此乘客信息，插入
            passengerService.book(passenger);
            return "success";

        }else{
            //数据库中已存在此乘客信息
            return "fail";
        }
    }
}
