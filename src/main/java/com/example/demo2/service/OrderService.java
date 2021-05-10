package com.example.demo2.service;

import com.example.demo2.bean.Order;
import com.example.demo2.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

@Component
@Service
public class OrderService implements OrderMapper {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> findByid(Integer user_id, int page, int limits) {
        List<Order> list = orderMapper.findByid(user_id,(limits-1)*page,page);
        if(list!=null){
            return list;
        }
        return null;
    }

    @Override
    public int countAllOrder() {
        int count = orderMapper.countAllOrder();
        if(count>0){
            return count;
        }
        return 0;
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
