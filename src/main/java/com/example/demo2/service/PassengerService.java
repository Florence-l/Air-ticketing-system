package com.example.demo2.service;

import com.example.demo2.bean.Passenger;
import com.example.demo2.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

@Component
@Service
public class PassengerService{
    @Resource
    private PassengerMapper passengerMapper;


    public int book(Passenger passenger) {
        passengerMapper.insert(passenger);
        return 1;
    }
}
