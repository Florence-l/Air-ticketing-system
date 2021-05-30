package com.example.demo2.controller;

import com.beust.jcommander.Parameters;
import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.service.OrderService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    public UserService userService;

    @GetMapping("/orderdetail")
    public String orderdetail(){
        return "orderResult";
    }

    @GetMapping("/userinfo")
    public String userinfo(){ return "userinfo";}

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

    @GetMapping("/userAdd")
    public String add(){
        return "userAdd";
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

        User user = userService.selectUserByName(principal.getName());
        Integer userid = user.getUserId();
        List<Order> orderList = orderService.findByid(userid,page,limit);
        int countOrder = orderService.countAllOrder();

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",orderList,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }

    @RequestMapping("unpay")
    @ResponseBody()
    public LayuiTableResultUtil<List> oderUnpay(Principal principal,HttpServletRequest request)
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

        User user = userService.selectUserByName(principal.getName());
        Integer userid = user.getUserId();
        List<Order> orderList = orderService.findUnpay(userid,page,limit);
        int countOrder = orderService.countAllOrder();

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",orderList,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }

    @RequestMapping("/usrname")
    @ResponseBody()
    public String username(Principal principal){
        User u = userService.selectUserByName(principal.getName());
        return u.getUsername();
    }
    @RequestMapping("/usremail")
    @ResponseBody()
    public String useremail(Principal principal){
        User u = userService.selectUserByName(principal.getName());
        return u.getEmail();
    }

    @RequestMapping("/usrsex")
    @ResponseBody()
    public String usersex(Principal principal){
        User u = userService.selectUserByName(principal.getName());
        return u.getSex().toString();
    }

    @RequestMapping("/usrbirth")
    @ResponseBody()
    public String userbirth(Principal principal){
        User u = userService.selectUserByName(principal.getName());
        return u.getBirthday();
    }

    @PostMapping("/insertInfo")
    public void updateInfo(String birthday, Integer sex, Principal principal){
        System.out.print(birthday);
        System.out.print(sex);
        User u = userService.selectUserByName(principal.getName());
        Integer userId = u.getUserId();
        userService.insertInfo(birthday,sex,userId);
    }

}
