package com.example.demo2.Util;

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





    }
}
