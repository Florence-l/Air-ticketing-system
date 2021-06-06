package com.example.demo2.controller;

import com.example.demo2.Util.LayuiTableResultUtil;
import com.example.demo2.Util.RequiredUtil;
import com.example.demo2.bean.Flight;
import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class FlightInquiryController {
    public static String getdeparturecity;
    public static String getarrivalcity;
    public static String getdate;

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


    //航班推荐(随机)
    @RequestMapping("/index/re")
    @ResponseBody()
    public List<Flight> recommend( Flight flight){
        List<Flight> recommendlist=flightService.findByRandom();
        return recommendlist;
    }

    @RequestMapping("/index/pr")
    @ResponseBody()
    public List<Flight> price(Flight flight){
        List<Flight> pricelist=flightService.findByPrice();
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
        System.out.println(getdeparturecity+getarrivalcity+getdate);

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
//
//    @RequestMapping("/ifAuthentication")
//    @ResponseBody
//    public String ifAuthentication(Principal principal){
//        try {
//            if(principal.getName()==null){
//                System.out.printf("\nprincipal is null\n");
//                return "null";
//            }
//            else {
//                System.out.printf("\nprincipal is not null\n");
//                return "notnull";
//            }
//
//        }catch (Exception e) {
//            return "null";
//        }
//    }


}