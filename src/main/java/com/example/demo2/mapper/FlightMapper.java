package com.example.demo2.mapper;

import com.example.demo2.bean.Flight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface FlightMapper {

    public List<Flight> findAll();
    public Flight select(long name);

    //public Flight findById();

}
