package com.example.demo2.controller;

import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Order;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    public OrderService orderService;

    @RequestMapping("order")
    @ResponseBody()
    public LayuiTableResultUtil<List> oderdetail(HttpServletRequest request)
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
}
