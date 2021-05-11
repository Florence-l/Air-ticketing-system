package com.example.demo2.mapper;

import com.example.demo2.bean.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlightMapper {
//    @Select("select * from flight_table")
//    List<Flight> findAll();
    public List<Flight> findByRequired(Flight flight);



}