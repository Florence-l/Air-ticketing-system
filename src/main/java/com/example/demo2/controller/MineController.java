package com.example.demo2.controller;

import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class MineController {
    @Autowired
    public OrderService orderService;
    private User user;

    @GetMapping("/orderdetail")
    public String orderdetail(){
        return "orderResult";
    }

    @GetMapping("/orderunpay")
    public String orderunpay(){
        return "orderUnpay";
    }

    @GetMapping("/mine")
    public String mine(){
        return "mine";

    }
    @GetMapping("/passengerdetail")
    public String passengerDetail(){
        return "passengerDetail";

    }
    @GetMapping ("/insert")
    public String insert(){
        return "passengerInsert";

    }

    @RequestMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();

    }


    @RequestMapping("order")
    @ResponseBody()
    public LayuiTableResultUtil<List> oderList(Principal principal,HttpServletRequest request)
    {

        RequiredUtil Required = new RequiredUtil();
        if(!Required.Required(request.getParameter("limit").trim())){
            return new LayuiTableResultUtil<List>("分页异常",null,1,10);
        }
        if(!Required.Required(request.getParameter("page").trim())){
            return new LayuiTableResultUtil<List>("分页异常",null,1,10);
        }
        int limit = Integer.parseInt(request.getParameter("limit").trim());
        int page = Integer.parseInt(request.getParameter("page").trim());


        List<Order> orderList = orderService.findByid(1,page,limit);
        int countOrder = orderService.countAllOrder();

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",orderList,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }
    @RequestMapping("unpay")
    @ResponseBody()
    public LayuiTableResultUtil<List> oderUnpay(HttpServletRequest request)
    {
        RequiredUtil Required = new RequiredUtil();
        if(!Required.Required(request.getParameter("limit").trim())){
            return new LayuiTableResultUtil<List>("分页异常",null,1,10);
        }
        if(!Required.Required(request.getParameter("page").trim())){
            return new LayuiTableResultUtil<List>("分页异常",null,1,10);
        }
        int limit = Integer.parseInt(request.getParameter("limit").trim());
        int page = Integer.parseInt(request.getParameter("page").trim());

        List<Order> orderList = orderService.findUnpay(1,page,limit);
        int countOrder = orderService.countAllOrder();

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",orderList,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }

}
