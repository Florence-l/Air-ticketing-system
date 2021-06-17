package com.example.demo2.controller;

import com.alipay.api.AlipayApiException;
import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import com.example.demo2.service.PayService;
import com.example.demo2.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;

@Controller
public class OrderController {
    public String gseat_status;

    @Autowired
    public OrderService orderService;
    @Autowired
    public UserService userService;
    @Autowired
    public FlightService flightService;
    @Autowired
    public PayService payService;



    @RequestMapping("/insertOrder")
    @ResponseBody()
    public String insertOrder(HttpServletRequest request, Principal principal) throws IOException{
        User user = userService.selectUserByName(principal.getName());
        Integer userid = user.getUserId();
        String user_name = request.getParameter("user_name");
        String passenger_id = request.getParameter("passenger_id");
        Integer flight_id = Integer.valueOf(request.getParameter("flight_id"));
        String seat_type = request.getParameter("seat_type");
        String orderTime = request.getParameter("orderTime");
        Integer paymentStatus = Integer.valueOf(request.getParameter("paymentStatus"));
        String realPrice = String.valueOf(request.getParameter("realPrice"));
        String order_num = request.getParameter("order_num");
        String change = request.getParameter("change");

        Order order = new Order(userid,user_name,passenger_id,flight_id,seat_type,orderTime,paymentStatus,realPrice,order_num,change);
        System.out.print(order);
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
        else {
            return "error";
        }
    }

    @RequestMapping("/findByoNum")
    @ResponseBody()
    public String findByoNum(HttpServletRequest request) throws IOException{
        String order_num = request.getParameter("order_num");
        String paymentTime = request.getParameter("paymentTime");
        ObjectMapper objectMapper = new ObjectMapper();
        String strObject = objectMapper.writeValueAsString(orderService.findByoNum(order_num,paymentTime));
//        System.out.println(strObject);
        return strObject;
    }



    @RequestMapping("/findById")
    @ResponseBody()
    public String findById(HttpServletRequest request) throws IOException{
        Integer order_id = Integer.parseInt(request.getParameter("order_id"));
        String paymentTime = request.getParameter("paymentTime");

        ObjectMapper objectMapper = new ObjectMapper();
        String strObject = objectMapper.writeValueAsString(orderService.searchByID(order_id,paymentTime));
        return strObject;
    }


    //改签补差价
    @RequestMapping("/change1")
    @ResponseBody()
    public String change1(HttpServletRequest request) throws IOException, AlipayApiException {
        Integer order_id = Integer.valueOf(request.getParameter("order_id"));
        String diff =request.getParameter("diff");
        Order order = orderService.searchById(order_id);
        String passenger_id = order.getPassenger_id();
        String order_num = order.getOrder_num() + passenger_id;
        Integer updateResult = orderService.updateChange("1",order_num,order_id);
        if(updateResult == 1){
            return "success";
        }
        return "fail";
    }

    //改签退差价
    @RequestMapping("/change2")
    @ResponseBody()
    public String change2(HttpServletRequest request) throws IOException, AlipayApiException {
        Integer order_id = Integer.valueOf(request.getParameter("order_id"));
        String diff =request.getParameter("diff");
        Order order = orderService.searchById(order_id);
        String order_num = order.getOrder_num();
        String payResult = payService.refund(order_num,diff,2);
        if(payResult == "success"){
            Integer updateResult = orderService.updateChange("2",order_num,order_id);
            if(updateResult == 1){
                return "success";
            }
        }
        return "fail";
    }

    //改签无差价
    @RequestMapping("/change3")
    @ResponseBody()
    public String change3(HttpServletRequest request) throws IOException, AlipayApiException {
        Integer order_id = Integer.valueOf(request.getParameter("order_id"));
        Order order = orderService.searchById(order_id);
        String order_num = order.getOrder_num();
        Integer updateResult = orderService.updateChange("3",order_num,order_id);
        if(updateResult == 1){
            return "success";
        }
        return "fail";
    }



    @RequestMapping("/ReturnTicket")
    @ResponseBody()
    public String ReturnTicket(HttpServletRequest request) throws IOException{
        String order_num = request.getParameter("order_num");
        Float realPrice = Float.parseFloat(request.getParameter("realPrice"))+50;
        Integer flight_id =Integer.parseInt(request.getParameter("flight_id"));
        Integer seat_id =0;
        if(request.getParameter("seat_id")!=""){
            seat_id=Integer.parseInt(request.getParameter("seat_id"));
        }
        Integer seat_type = Integer.parseInt(request.getParameter("seat_type"));
        Integer order_id = Integer.parseInt(request.getParameter("order_id"));
        String change0 = request.getParameter("change0");
        System.out.println("change0"+change0);

//        try {
//            System.out.println("test:"+payService.refund(order_num,realPrice.toString(),0));
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }


        try {
            if(payService.refund(order_num,realPrice.toString(), Integer.parseInt(change0)) == "success"){
                if(seat_id!=null) {
                    gseat_status=flightService.findSeatId(flight_id);
                    deleteSeat(gseat_status,seat_id);
                    flightService.updateSeatStatus(flight_id,gseat_status);
                    System.out.println(gseat_status);
                    if(seat_type==1){
                        flightService.deleteBC(flight_id);
                    }
                    else{
                        flightService.deleteEC(flight_id);
                    }
                }
                orderService.ReturnTicket(order_id);
                return "success";
            } else {System.out.println(payService.refund(order_num,realPrice.toString(), Integer.parseInt(change0)));}
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
       return "false";

    }

    public void deleteSeat(String seat, int id){
        char[] seatstatus=seat.toCharArray();
        seatstatus[id-1]='0';
        seat= Arrays.toString(seatstatus).replaceAll("[\\[\\]\\s,]", "");
        gseat_status=seat;
    }

}
