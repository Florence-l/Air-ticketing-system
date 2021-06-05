package com.example.demo2.controller;


import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.service.OrderService;
import com.example.demo2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    public OrderService orderService;
    @Autowired
    public UserService userService;

    @RequestMapping("/insertOrder")
    @ResponseBody()
    public String insertOrder(HttpServletRequest request, Principal principal) throws IOException{
        User user = userService.selectUserByName(principal.getName());
        Integer userid = user.getUserId();
        String user_name = request.getParameter("user_name");
        String passenger_id = request.getParameter("passenger_id");
        Integer flght_id = Integer.valueOf(request.getParameter("flight_id"));
        String orderTime = request.getParameter("orderTime");
        Integer paymentStatus = Integer.valueOf(request.getParameter("paymentStatus"));
        Float realPrice = Float.valueOf(request.getParameter("realPrice"));
        String order_num = request.getParameter("order_num");
        Order order = new Order(userid,user_name,passenger_id,flght_id,orderTime,paymentStatus,realPrice,order_num);
        System.out.print(order);
        orderService.insertOrder(order);
        return "success";
    }

    @RequestMapping("/selectOdByNum")
    @ResponseBody()
    public String selectOdByNum(HttpServletRequest request) throws IOException {
        String order_num = request.getParameter("order_num");
        String passenger_id = request.getParameter("passenger_id");
//        System.out.println(orderService.findByNum(order_num,passenger_id));
        Order order = orderService.findByNum(order_num, passenger_id);
        if (order != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String orderJson = objectMapper.writeValueAsString(order);
            System.out.println(orderJson);
            return orderJson;
        }
        else return "error";
    }


}
