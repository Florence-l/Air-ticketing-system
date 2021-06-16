package com.example.demo2.mapper;

import com.example.demo2.bean.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
@Mapper
public interface FlightMapper {
    List<Flight> findByRequired(@Param("departurecity")String departurecity, @Param("arrivalcity")String arrivalcity, @Param("date") String date, @Param("page") int page, @Param("limits") int limits);
    List<Flight> findByDAA(@Param("departurecity")String departurecity, @Param("arrivalcity")String arrivalcity,@Param("page") int page, @Param("limits") int limits);
    String findSeatId(@Param("flight_id") Integer flight_id);
    List<Flight> findByRandom();
    List<Flight> findByPrice();
    int updateSeatStatus(Integer flight_id,String seat_status);
    int updateBC(Integer flight_id);
    int updateEC(Integer flight_id);
    int deleteBC(Integer flight_id);
    int deleteEC(Integer flight_id);
    int countAllFlight();


}