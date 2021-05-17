package com.example.demo2.service;

import com.example.demo2.bean.Flight;
import com.example.demo2.mapper.FlightMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@Service
public class FlightService implements FlightMapper{
    @Resource
    private FlightMapper flightMapper;

    @Override
    public List<Flight> findByRequired(String departurecity, String arrivalcity, String date,int page, int limits) {
        List<Flight> list = flightMapper.findByRequired(departurecity,arrivalcity,date,(limits-1)*page,page);
        if(list!=null){
            return list;
        }
        return null;
    }
    @Override
    public int countAllFlight() {
        int count = flightMapper.countAllFlight();
        if(count>0){
            return count;
        }
        return 0;
    }

    @Override
    public List<Flight> findByid(Integer flight_id) {
        List<Flight> list = flightMapper.findByid(flight_id);
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public List<Flight> findByRandom(){
        List<Flight> list = flightMapper.findByRandom();
        if(list!=null){
            return list;
        }
        return null;
    }

    /**
     * 计算航班价格(直接调用次函数)
     * @param seattype 舱位类型 经济舱 0  商务舱 1
     * @param age 年龄，判断是否为儿童
     * @param date 起飞时间
     * @param price 原始价格
     * @return 折扣后的价格
     * @throws ParseException
     */
    public int priceCalculate(int seattype, int age, String date,int price) throws ParseException {
       //根据舱位 经济舱6.7折
        if(seattype==0) price*=0.67;
        //判断是否为儿童[2,12) 5折
        if(2<=age&&age<12) price*=0.5;
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        //将时间转换为标准时间格式
        date=date.replaceAll("\\.","-");
        //订票日期
        Date bookingDate = simpleFormat.parse(simpleFormat.format(calendar.getTime()));
        //出发日期
        Date flyDate = simpleFormat.parse(date);
        long from1 = bookingDate.getTime();
        long to1 = flyDate.getTime();
        //天数差
        int days = (int) ((to1 - from1) / (1000 * 60 * 60*24));
        Random random=new Random();
        if(days==0);//当天，原价
        else if(days>0&&days<=3){//0~3天
            price*=(0.9+(3-days)*0.01);
        }
        else if(days>3&&days<=7){//3~7天
            price*=(0.85+(7-days)*0.01);
        }
        else if (days>7&&days<=14){//7~14天
            price*=(0.75+(14-days)*0.01);
        }
        else if (days>14&&days<30){//7.0~7.5
            price*=(0.7+(random.nextDouble()/20));
        }
        else if (days==30) {//一个月 7.0折
            price*=0.7;
        }
        else if(days>30&&days<60){//6.5~7.0
            price*=(0.65+(random.nextDouble()/20));
        }
        else if(days>=60){//两个月
            price*=0.65;
        }
        return price;
    }


}
