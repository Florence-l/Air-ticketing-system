package com.example.demo2.test;

import com.example.demo2.bean.Flight;
import com.example.demo2.mapper.FlightMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        InputStream inputStream= Test.class.getClassLoader().getResourceAsStream("generatorConfig.xml");
        SqlSessionFactoryBuilder sessionFactoryBuilder=new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory=sessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //获取实现接口的代理对象
        FlightMapper flightMapper=sqlSession.getMapper(FlightMapper.class);
        List<Flight> list = flightMapper.findAll();
        for(Flight flight:list){
            System.out.println(flight);
        }

    }
}
