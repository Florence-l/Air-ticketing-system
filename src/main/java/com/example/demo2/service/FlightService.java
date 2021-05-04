package com.example.demo2.service;


import com.example.demo2.bean.Flight;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FlightService {

    @Autowired
    private FlightService service;


    void test(){
        Map<String,Integer> map=new HashMap<>();
        //  map.put("flight_id",1);
        //service.listByMap(map).forEach(System.out::println);
    }

}
