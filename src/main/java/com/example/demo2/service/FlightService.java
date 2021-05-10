package com.example.demo2.service;


import com.example.demo2.bean.Flight;

import java.util.List;


public interface FlightService {

    List<Flight> findByRequired(Flight flight);
//    List<Flight> findAll();

}
