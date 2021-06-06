package com.example.demo2.controller;

import com.example.demo2.bean.Order;
import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;


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

    @GetMapping("/seatchooseBack")
    public String seatchooseBack(){ return "seatchooseBack";}


    @RequestMapping("/setseat")
    @ResponseBody()
    public void updateseat(@RequestParam("seat_id")Integer seat_id, @RequestParam("order_id")Integer order_id,
                           @RequestParam("flight_id")Integer flight_id, @RequestParam("type")Integer type) {

        System.out.println("seat="+seat_id+" order_id="+order_id+" flight_id="+flight_id+" type="+type);
        gseat_id=seat_id;
        gorder_id=order_id;
        gflight_id=flight_id;
        gseat_status=flightService.findSeatId(gflight_id);
        update(gseat_status,gseat_id);
        flightService.updateSeatStatus(flight_id,gseat_status);
        System.out.println(gseat_status);
        orderService.updateSeat(gseat_id,gorder_id);

    }

    @RequestMapping("/choosing")
    @ResponseBody()
    public String choose(@RequestParam("flight_id")Integer flight_id){
        String seat_status = flightService.findSeatId(flight_id);
        String unset = String.valueOf(getunavailable(seat_status));
        unset="{\"unset\":"+unset+"}";
        System.out.println(unset);
        return unset;

    }


//更新seat_status
    public void update(String seat,int id){
        char[] seatstatus=seat.toCharArray();
        seatstatus[id-1]='1';
        seat= Arrays.toString(seatstatus).replaceAll("[\\[\\]\\s,]", "");
        gseat_status=seat;
    }
//获取被占用位
    public List<Integer> getunavailable(String status){
        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<status.length();i++){
            char j=status.charAt(i);
            if(j=='1'){
                list.add(i+1);
            }
        }
        return list;
    }



}
