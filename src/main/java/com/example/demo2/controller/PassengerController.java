package com.example.demo2.controller;

import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Order;
import com.example.demo2.bean.Passenger;
import com.example.demo2.bean.User;
import com.example.demo2.service.PassengerService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    @Autowired
    private UserService userService;

    @RequestMapping("/book")
    @ResponseBody()
    //判断数据库中是否存在用户所输入乘客信息 不存在：插入信息，返回success；存在：不对数据库做改动
    public String passengerresult(HttpServletRequest request,Principal principal) throws IOException {
        String user_name = request.getParameter("user_name");
        String passenger_id = request.getParameter("passenger_id");
        String user_tel = request.getParameter("user_tel");
        String name = principal.getName();
        String user_id = (userService.selectUserByName(name).getUserId()).toString();


        Passenger passenger = new Passenger(user_name, passenger_id, user_tel,user_id);
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

    @RequestMapping("/delPassenger")
    @ResponseBody()
    public String delPassenger(HttpServletRequest request) throws IOException{
        String passenger_id = request.getParameter("passenger_id");
        passengerService.deleteByMineId(passenger_id);
        return "success";
    }

    @RequestMapping("/updateTel")
    @ResponseBody()
    public int updateTel(HttpServletRequest request) throws IOException{
        String user_tel = request.getParameter("user_tel");
        String passenger_id = request.getParameter("passenger_id");
        passengerService.updateTel(passenger_id,user_tel);
        return 1;
    }


}
