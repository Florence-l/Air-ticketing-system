package com.example.demo2.controller;

import com.example.demo2.bean.Flight;
import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FlightInquiryController {
    public static String getdeparturecity;
    public static String getarrivalcity;
    public static String getdate;
    public static String departurecity;
    public static String arrivalcity;

    @Autowired
    public FlightService flightService;
    @Autowired
    public OrderService orderService;

    @GetMapping("/result")
    public String showresult() {
        return "flightSchedule";
    }

    @GetMapping("/index")
    public String showindex(){
        return "index";
    }

    @GetMapping("/book")
    public String showbook(){
        return "booking";
    }

    @GetMapping("/change")
    public String showchange(){
        return "flightChange";
    }


    @GetMapping("/orderChange")
    public String orderChange(){
        return "orderChange";
    }

    @GetMapping("/boardingPass")
    public String board(){
        return "boardingPass";
    }

    //航班推荐(随机)
    @RequestMapping("/index/re")
    @ResponseBody()
    public List<Flight> recommend(){
        List<Flight> recommendlist=flightService.findByRandom();
        return recommendlist;
    }

    @RequestMapping("/index/pr")
    @ResponseBody()
    public List<Flight> price(){
        List<Flight> pricelist=flightService.findByPrice();
        System.out.println(pricelist);
        return pricelist;
    }


    //航班查询
    @PostMapping("/index")
    public void query( String departurecity, String arrivalcity, String date, Model model){
        getdeparturecity=departurecity;
        getarrivalcity=arrivalcity;
        getdate=date;

        model.addAttribute("departurecity",getdeparturecity);
        model.addAttribute("arrivalcity",getarrivalcity);
        model.addAttribute("date",getdate);
        System.out.println("1"+getdeparturecity+getarrivalcity+getdate);
    }


    @RequestMapping("flight")
    @ResponseBody()
    public List<Flight> flightresult() {
        System.out.println("2"+getdeparturecity+getarrivalcity+getdate);
        List<Flight> flightList = flightService.findByRequired(getdeparturecity,getarrivalcity,getdate);
        return flightList;
    }


    @RequestMapping("postDAA")
    @ResponseBody()
    public void postDAA(HttpServletRequest request){
        departurecity = request.getParameter("departurecity");
        arrivalcity = request.getParameter("arrivalcity");
    }

    @RequestMapping("changeTicket")
    @ResponseBody()
    public List<Flight> changeTicket() {

        System.out.println("hahaha"+departurecity);
        List<Flight> flightList = flightService.findByDAA(departurecity,arrivalcity);
        if (flightList != null) {
            return flightList;
        }
        return null;
    }
}