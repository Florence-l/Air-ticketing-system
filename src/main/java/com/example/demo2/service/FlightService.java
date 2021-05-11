package com.example.demo2.service;

import com.example.demo2.bean.Flight;
import com.example.demo2.mapper.FlightMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

@Component
@Service
public class FlightService implements FlightMapper{
    @Resource
    private FlightMapper flightMapper;

    @Override
    public List<Flight> findByRequired(String departurecity, String arrivalcity, String date,int page, int limits) {
        List<Flight> list = flightMapper.findByRequired(departurecity,arrivalcity,date,(limits-1)*page,page);
        if(list!=null){
            return list;
        }
        return null;
    }
    @Override
    public int countAllFlight() {
        int count = flightMapper.countAllFlight();
        if(count>0){
            return count;
        }
        return 0;
    }

    @Override
    public List<Flight> findByid(Integer flight_id) {
        List<Flight> list = flightMapper.findByid(flight_id);
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public List<Flight> findByRandom(){
        List<Flight> list = flightMapper.findByRandom();
        if(list!=null){
            return list;
        }
        return null;
    }
}
