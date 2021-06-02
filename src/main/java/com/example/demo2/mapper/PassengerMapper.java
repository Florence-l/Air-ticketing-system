package com.example.demo2.mapper;


import com.example.demo2.bean.Passenger;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 对乘客信息表进行操作
 */
@Mapper
public interface PassengerMapper {

    int insert(Passenger passenger);

    int deleteById(Passenger passenger);

    Passenger selectById(Passenger passenger);

}

