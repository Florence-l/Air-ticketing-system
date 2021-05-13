package com.example.demo2.controller;

import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Flight;
import com.example.demo2.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

@Controller
public class FlightInquiryController {
    public static String getdeparturecity;
    public static String getarrivalcity;
    public static String getdate;

    @Autowired
    public FlightService flightService;

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

    //航班推荐(随机)
    @RequestMapping("/index/re")
    @ResponseBody()
    public List<Flight> recommend(Flight flight){
        List<Flight> recommendlist=flightService.findByRandom();
        return recommendlist;
    }

    //航班查询
    @PostMapping("/index")
    public String query( String departurecity, String arrivalcity, String date, Model model){
       getdeparturecity=departurecity;
       getarrivalcity=arrivalcity;
       getdate=date;

       model.addAttribute("departurecity",getdeparturecity);
       model.addAttribute("arrivalcity",getarrivalcity);
       model.addAttribute("date",getdate);
       System.out.println(getdeparturecity+getarrivalcity+getdate);

        return "redirect:/result";
    }

    @RequestMapping("flight")
    @ResponseBody()
    public LayuiTableResultUtil<List> flightresult(HttpServletRequest request) {
        System.out.println(getdeparturecity+getarrivalcity+getdate);
        RequiredUtil Required = new RequiredUtil();
        if (!Required.Required(request.getParameter("limit").trim())) {
            return new LayuiTableResultUtil<List>("分页异常", null, 1, 10);
        }
        if (!Required.Required(request.getParameter("page").trim())) {
            return new LayuiTableResultUtil<List>("分页异常", null, 1, 10);
        }
        int limit = Integer.parseInt(request.getParameter("limit").trim());
        int page = Integer.parseInt(request.getParameter("page").trim());


        List<Flight> flightList = flightService.findByRequired(getdeparturecity,getarrivalcity,getdate ,page, limit);
        int countflight = flightService.countAllFlight();

        LayuiTableResultUtil<List> list = new LayuiTableResultUtil<List>("", flightList, 0, countflight);
        if (flightList != null) {
            return list;
        }

        return null;
    }


}


