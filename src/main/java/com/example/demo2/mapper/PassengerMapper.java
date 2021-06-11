package com.example.demo2.mapper;


import com.example.demo2.bean.Passenger;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 对乘客信息表进行操作
 */
@Mapper
public interface PassengerMapper {

    int insert(Passenger passenger);

    int deleteById(Passenger passenger);

    void deleteByMineId(String passenger_id);

    Passenger selectById(Passenger passenger);

    List<Passenger> selectByUser(String user_id, @Param("page") int page, @Param("limits") int limits);

    int countAllPassenger(String user_id);

}

