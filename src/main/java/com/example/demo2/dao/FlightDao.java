package com.example.demo2.dao;

import com.example.demo2.bean.Flight;
import com.example.demo2.mapper.FlightMapper;
import com.example.demo2.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FlightDao {
    @Autowired
    FlightService flightService;

    Map<String, Flight> map=null;

    //按照要求查询航班
    public Map<String,Flight>getRequiredmap(Flight flight){
        map=new HashMap<>();
        List<Flight> flights=flightService.findByRequired(flight);
        System.out.println(flights.size());
        for(int i=0;i<flights.size();i++){
        map.put(String.valueOf(flights.get(i).getFlightId()),flights.get(i));
   }
        return map;
  }

//按照要求查询航班
    public Collection<Flight> getRequired(Flight flight){
        this.getRequiredmap(flight);
        return map.values();
  }


}

