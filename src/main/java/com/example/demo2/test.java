package com.example.demo2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo2Application.class)
@Slf4j
public class test {
    @Test
    public void priceCalculate() throws ParseException {
        int seattype=0;
        int age=20;
        String date="2021.07.30";
        int price=1000;
        //根据舱位
        if(seattype==0){//经济舱
            price*=0.67;
        }
        if(2<=age&&age<12)//儿童打5折
        {
            price*=0.5;
        }
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
        int days = (int) ((to1 - from1) / (1000 * 60 * 60*24));
        System.out.println(days);
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
        System.out.println(price);

    }
}
