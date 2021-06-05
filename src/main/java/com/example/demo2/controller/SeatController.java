package com.example.demo2.controller;

import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@Controller
public class SeatController {
    public Integer gseat_id;
    public Integer gorder_id;
    public Integer gflight_id;
    public String gseat_status;

    @Autowired
    public OrderService orderService;

    @Autowired
    public FlightService flightService;

    @GetMapping("/seatchooser")
    public String show(){
        return "seatchooser";
    }


    @RequestMapping("/setseat")
    @ResponseBody
    public void updateseat(@RequestParam("seat_id")Integer seat_id,@RequestParam("order_id")Integer order_id,@RequestParam("flight_id")Integer flight_id) {

        System.out.println("seat="+seat_id+" order_id="+order_id+" flight_id="+flight_id);
        gseat_id=seat_id;
        gorder_id=order_id;
        gflight_id=flight_id;

        gseat_status=flightService.findByid(gflight_id);
        update(gseat_status,gseat_id);
        flightService.updateSeatStatus(flight_id,gseat_status);
        System.out.println(gseat_status);
        orderService.updateSeat(gseat_id,gorder_id);
    }


    public void update(String seat,int id){
        char[] seatstatus=seat.toCharArray();
        seatstatus[id-1]='1';
        seat= Arrays.toString(seatstatus).replaceAll("[\\[\\]\\s,]", "");
        gseat_status=seat;
    }









}
