package com.example.demo2.controller;

import com.example.demo2.bean.Passenger;
import com.example.demo2.service.PassengerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @RequestMapping("/book")
    @ResponseBody()
    //判断数据库中是否存在用户所输入乘客信息 不存在：插入信息，返回success；存在：不对数据库做改动
    public String passengerresult(HttpServletRequest request) throws IOException {
        //String[][] p = (String[][]) request.getAttribute("array");
        //String[] p = request.getParameterValues("array[]");
        //String p = request.getParameter("array");
        //System.out.println(p);
        //System.out.println(p[0]);

        //ObjectMapper objectMapper = new ObjectMapper();
        //JsonNode jsonNode = objectMapper.readTree(p);
        //String user_name = jsonNode.get("0").asText();
        //System.out.println(user_name);

        //String pJson = "{ \"user_name\" : \"123\",\"passenger_id\" : \"456\", \"user_tel\" : 789 }";
        /*String pJson = p[0];
        try {
            Passenger passenger = objectMapper.readValue(pJson, Passenger.class);
            System.out.println(passenger.getUser_name());
            System.out.println(passenger.getPassenger_id());
            System.out.println(passenger.getUser_tel());
        } catch (IOException e) {
            e.printStackTrace();
        }*/




        //var jsonData = JSON.stringify(data);// 转成JSON格式
        //var result = $.parseJSON(jsonData);// 转成JSON对象

        //Integer length = p.length;
        //System.out.println(length);

        //String user_name;
        //Integer[] passenger_id = {0};
        //Integer[] user_tel = {0};
        //Passenger[] pArray = new Passenger[5];

        /*for (int i = 0; i < length - 1; i++) {
            user_name = p[i].0;
            passenger_id[i] = Integer.parseInt(p[i][1]);
            user_tel[i] = Integer.parseInt(p[i][2]);

            pArray[i] = new Passenger(user_name[i], passenger_id[i], user_tel[i]);
            Passenger result = passengerService.selectAllPassenger(pArray[i]);
            if (result == null) {
                //数据库中不存在此乘客信息，插入
                passengerService.book(pArray[i]);
            }
        }*/
        return "success";
    }
}
