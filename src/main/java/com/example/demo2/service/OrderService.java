package com.example.demo2.service;

import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.mapper.OrderMapper;
import com.example.demo2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;

@Component
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
//    private UserMapper userMapper;


    public List<Order> findByid(Integer user_id, int page, int limits) {
//        User user = userMapper.loadUserByUsername(username);
//        Integer user_id = user.getUserId();
        List<Order> list = orderMapper.findByid(user_id,(limits-1)*page,page);
        if(list!=null){
            return list;
        }
        return null;
    }

//    public User findUser(String username) {
//        return userMapper.loadUserByUsername(username);
//    }
    public int countAllOrder() {
        int count = orderMapper.countAllOrder();
        if(count>0){
            return count;
        }
        return 0;
    }

    public List<Order> findUnpay(Integer user_id, int page, int limits){
//        User user = userMapper.selectUserByName(user_name);
//        Integer user_id=user.getUserId();
        List<Order> list = orderMapper.findUnpay(user_id,(limits-1)*page,page);
        if(list!=null){
            return list;
        }
        return null;
    }

    public int insertOrder(Order order){
        orderMapper.insertOrder(order);
        return 1;
    }

//    @Override
//    public List<Order> findUnpayByid(Integer userid) {
//        return orderMapper.findUnpayByid(userid);
//    }

//    @Override
//    public int insertOrder(Order order) {
//        return 0;
//    }
//
//    @Override
//    public int deleteOrder() {
//        return 0;
//    }
}
