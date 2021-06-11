package com.example.demo2.service;

import com.example.demo2.bean.Passenger;
import com.example.demo2.mapper.PassengerMapper;
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

    public int deleteById(Passenger passenger) {
        passengerMapper.deleteById(passenger);
        return 1;
    }

    public void deleteByMineId(String passenger_id) {
        passengerMapper.deleteByMineId(passenger_id);
    }

    public Passenger selectAllPassenger(Passenger passenger){
        Passenger p = passengerMapper.selectById(passenger);
        return p;
    }

    public List<Passenger> selectByUser(String user_id, int page, int limits){
        List<Passenger> p = passengerMapper.selectByUser(user_id,(limits-1)*page,page);
        if(p!=null){
            return p;
        }
        return null;

    }

    public int countAllPassenger(String user_id) {
        int count = passengerMapper.countAllPassenger(user_id);
        if(count>0){
            return count;
        }
        return 0;
    }
}
