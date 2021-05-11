package com.example.demo2.service;

import com.example.demo2.bean.Flight;
import com.example.demo2.dao.FlightDao;
import com.example.demo2.mapper.FlightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightImplement implements FlightService{
    @Autowired
    FlightMapper flightMapper;
    FlightDao flightDao;

    @Override
    public List<Flight> findByRequired(Flight flight) {
        return flightMapper.findByRequired(flight);
    }
//    @Override
//    public List<Flight> findAll(){
//        return flightDao.findAll();
//    }
}
