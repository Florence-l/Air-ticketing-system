package com.example.demo2.controller;


import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import com.example.demo2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Controller
public class OrderController {
    @Autowired
    public OrderService orderService;
    @Autowired
    public UserService userService;
    @Autowired
    public FlightService flightService;


    @RequestMapping("/insertOrder")
    @ResponseBody()
    public String insertOrder(HttpServletRequest request, Principal principal) throws IOException{
        User user = userService.selectUserByName(principal.getName());
        Integer userid = user.getUserId();
        String user_name = request.getParameter("user_name");
        String passenger_id = request.getParameter("passenger_id");
        System.out.print(passenger_id);
        Integer flight_id = Integer.valueOf(request.getParameter("flight_id"));
        String seat_type = request.getParameter("seat_type");
        System.out.print(seat_type);
        String orderTime = request.getParameter("orderTime");
        Integer paymentStatus = Integer.valueOf(request.getParameter("paymentStatus"));
        Float realPrice = Float.valueOf(request.getParameter("realPrice"));
        String order_num = request.getParameter("order_num");
        Order order = new Order(userid,user_name,passenger_id,flight_id,seat_type,orderTime,paymentStatus,realPrice,order_num);
        //System.out.print(order);
        orderService.insertOrder(order);
        return "success";
    }


    @RequestMapping("/selectOdByNum")
    @ResponseBody()
    public String selectOdByNum(HttpServletRequest request) throws IOException {
        String order_num = request.getParameter("order_num");
        String passenger_id = request.getParameter("passenger_id");
        //System.out.println(orderService.findByNum(order_num,passenger_id));
        Order order = orderService.findByNum(order_num, passenger_id);
        if (order != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);
            System.out.println(orderJson);
            return orderJson;
        }
        else return "error";
    }

    @RequestMapping("/findByoNum")
    @ResponseBody()
    public String findByoNum(HttpServletRequest request) throws IOException{
        String order_num = request.getParameter("order_num");
        ObjectMapper objectMapper = new ObjectMapper();
        String strObject = objectMapper.writeValueAsString(orderService.findByoNum(order_num));
//        System.out.println(strObject);
        return strObject;
    }

    @RequestMapping("/findById")
    @ResponseBody()
    public String findById(HttpServletRequest request) throws IOException{
        Integer order_id = Integer.parseInt(request.getParameter("order_id"));
        ObjectMapper objectMapper = new ObjectMapper();
        String strObject = objectMapper.writeValueAsString(orderService.findById(order_id));
//        System.out.println(strObject);
        return strObject;
    }


}
