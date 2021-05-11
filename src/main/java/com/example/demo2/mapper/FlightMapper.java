package com.example.demo2.mapper;

import com.example.demo2.bean.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface FlightMapper {
    List<Flight> findByRequired(@Param("departurecity")String departurecity, @Param("arrivalcity")String arrivalcity, @Param("date") String date, @Param("page") int page, @Param("limits") int limits);
    List<Flight> findByid(@Param("flight_id") Integer flight_id, @Param("page") int page, @Param("limits") int limits);

    int countAllFlight();



}