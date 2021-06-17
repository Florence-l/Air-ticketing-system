package com.example.demo2.Util;

import com.example.demo2.bean.Order;
import com.example.demo2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
@Service
public class ScheduTask {
    @Autowired
    public OrderService orderService;


    @Scheduled(cron = "*/5 * * * * ?")
    public void testSca(){
//        String data_bef = "2021.6.6 21:1:43";
        List<String> data_befo = orderService.scheduleTable();
        List<Order> goTime = orderService.goTime();

        for (Object item : data_befo) {
            String s = (String) item;
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar before = Calendar.getInstance();
            before.setTime(date);
            before.add(Calendar.HOUR_OF_DAY,2);

            Calendar after=Calendar.getInstance();
            if(before.before(after)){
                    orderService.updateStatus(s);
//                System.out.println("超出2小时");
            }

        }
        for(Order item_:goTime){

            String data = item_.getFlight().getDate();
            String departuretime = item_.getFlight().getDeparturetime();
            Integer order_id = item_.getOrder_id();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
            SimpleDateFormat sdf_=new SimpleDateFormat("HH:mm:ss");
            try {
                //获取航班时间
                Date datatime= sdf.parse(data);

                Date sfm = sdf_.parse(departuretime);
                Calendar cal=Calendar.getInstance();//使用日历类
                cal.setTime(sfm);
                int hour = cal.get(Calendar.HOUR_OF_DAY);//获得时
                int minite = cal.get(Calendar.MINUTE);//获得分
                int second = cal.get(Calendar.SECOND);//获得秒
                Date returnDate=new Date();
                Calendar calNow=Calendar.getInstance();//使用日历类
                calNow.setTime(returnDate);
                int hourNow = cal.get(Calendar.HOUR_OF_DAY);//获得时
                int miniteNow = cal.get(Calendar.MINUTE);//获得分
                int secondNow = cal.get(Calendar.SECOND);//获得秒
                if(datatime.before(returnDate)) {orderService.updateGo(order_id);} //将is_finish 置为1
                else if(hour < hourNow) {orderService.updateGo(order_id);}//
                else if(hour == hourNow && minite<miniteNow){orderService.updateGo(order_id);}
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //获取当前时间




        }





    }
}
