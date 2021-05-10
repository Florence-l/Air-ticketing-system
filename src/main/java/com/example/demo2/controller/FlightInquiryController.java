package com.example.demo2.controller;

import com.example.demo2.bean.Flight;
//import com.example.demo2.service.FlightService;
import com.example.demo2.dao.FlightDao;
import com.example.demo2.mapper.FlightMapper;
import com.example.demo2.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class FlightInquiryController {
    @Autowired
    FlightDao flightDao;
    FlightService flightService;
    @GetMapping("/test")
    public String show(){
        return "test";
    }

    @GetMapping("/flightInquiry")
    public String show1(){
        return "flightInquiry";
    }
    @GetMapping("/test2")
    public String test2(){
        return "test";
    }
//
//    @RequestMapping("/flihgtlist")
//    public String findAll(Model model){
//        List<Flight> flightlist=flightService.findAll();
//        model.addAttribute("flightlist",flightlist);
//        return "list";
//    }

//    public FlightService flightService;
//
//    @RequestMapping("/findAll")
//    public List<Flight> findAll(Model model){
//        List<Flight> all= flightService.findAll();
//        System.out.println(all+"++++++++++");
//
//        return all;
//    }
//
//
//
////
//    @PostMapping("/find_flight_required")
//    public Collection<Flight> find_flight_required(@RequestParam("departurecity") String departurecity,
//                                                   @RequestParam("arrivalcity") String arrivalcity,
//                                                   @RequestParam("date") Date date,
//                                                   Model model){
//        Flight fli = new Flight();
//        fli.setDeparturecity(departurecity);
//        fli.setArrivalcity(arrivalcity);
//        fli.setDate(date);
//
//        Collection<Flight> flight= flightDao.getRequired(fli);
//        System.out.println(flight);
//        model.addAttribute("flight",flight);
//        return "index";
//    }







}
