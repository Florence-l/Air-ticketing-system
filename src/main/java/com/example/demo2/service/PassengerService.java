package com.example.demo2.service;

import com.example.demo2.bean.Flight;
import com.example.demo2.bean.Passenger;
import com.example.demo2.mapper.PassengerMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

@Component
@Service
public class PassengerService implements PassengerMapper{
    @Resource
    private PassengerMapper passengerMapper;


    public int book(Passenger passenger) {
        passengerMapper.insert(passenger);
        return 1;
    }

    @Override
    public int insert(Passenger passenger) {
        return 0;
    }

    public int deleteById(Passenger passenger) {
        passengerMapper.deleteById(passenger);
        return 1;
    }

    @Override
    public Passenger selectById(Passenger passenger) {
        return null;
    }

    public Passenger selectAllPassenger(Passenger passenger){
        Passenger p = passengerMapper.selectById(passenger);
        return p;
    }

/*    @Override
    public List<Passenger> queryList(){
        List<Passenger> list = passengerMapper.queryList();
        if(list!=null){
            return list;
        }
        return null;
    }*/
}
