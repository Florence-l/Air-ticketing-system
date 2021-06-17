package com.example.demo2.controller;

import com.beust.jcommander.Parameters;
import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Order;
import com.example.demo2.bean.Passenger;
import com.example.demo2.bean.User;
import com.example.demo2.service.OrderService;
import com.example.demo2.service.PassengerService;
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
    @Autowired
    public PassengerService passengerService;

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

        List<Order> list_rel = null;
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
        if(orderList.size()>3) {
            list_rel = orderList.subList(0,3);
        } else {
            list_rel = orderList;
        }
        int countOrder = orderService.countAllOrder(userid);

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",list_rel,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }

    @RequestMapping("orderAll")
    @ResponseBody()
    public LayuiTableResultUtil<List> orderAll(Principal principal,HttpServletRequest request)
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
        int countOrder = orderService.countAllOrder(userid);

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
        List<Order> list_rel = null;
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
        if(orderList.size()>3) {
            list_rel = orderList.subList(0,3);
        } else {
            list_rel = orderList;
        }
        int countOrder = orderService.countUnpayOrder(userid);

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",list_rel,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }

    @RequestMapping("unpayAll")
    @ResponseBody()
    public LayuiTableResultUtil<List> oderUnpayAll(Principal principal,HttpServletRequest request)
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
        int countOrder = orderService.countUnpayOrder(userid);

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",orderList,0,countOrder);
        if(orderList!=null){
            return list;
        }
        return null;
    }


    @RequestMapping("minePassenger")
    @ResponseBody()
    public LayuiTableResultUtil<List> minePassenger(Principal principal,HttpServletRequest request)
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
        String userid = user.getUserId().toString();
        List<Passenger> passengerList = passengerService.selectByUser(userid,page,limit);
        int countOrder = passengerService.countAllPassenger(userid);

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("",passengerList,0,countOrder);
        if(passengerList!=null){
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
        try{
            User u = userService.selectUserByName(principal.getName());
            return u.getSex().toString();
        } catch (Exception e) {
            System.out.printf("\n sex 为空\n");
            return "";
        }

    }

    @RequestMapping("/usrbirth")
    @ResponseBody()
    public String userbirth(Principal principal){
        User u = userService.selectUserByName(principal.getName());
        return u.getBirthday();
    }
    @RequestMapping("/usrimage")
    @ResponseBody()
    public String userimage(Principal principal){
        User u = userService.selectUserByName(principal.getName());
        return u.getImage();
    }

    @RequestMapping("/insertInfo")
    @ResponseBody
    public void updateInfo(String birthday, Integer sex, Principal principal){
        User u = userService.selectUserByName(principal.getName());
        Integer userId = u.getUserId();
        userService.insertInfo(birthday,sex,userId);
    }
    @RequestMapping("/insertImage")
    @ResponseBody
    public void updateImage(String image, Principal principal){
        User u = userService.selectUserByName(principal.getName());
        Integer userId = u.getUserId();
        userService.insertImage(image,userId);
    }


}
