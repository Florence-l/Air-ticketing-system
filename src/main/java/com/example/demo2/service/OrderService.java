package com.example.demo2.service;

import com.example.demo2.bean.Order;
import com.example.demo2.bean.User;
import com.example.demo2.mapper.OrderMapper;
import com.example.demo2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

@Component
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;
//    private UserMapper userMapper;


    public List<Order> findByid(Integer user_id, int page, int limits) {
        List<Order> list = orderMapper.findByid(user_id,(limits-1)*page,page);
        if(list!=null){
            return list;
        }
        return null;
    }

    public Order findByNum(String order_num,String passenger_id){
        Order order = orderMapper.findByNum(order_num,passenger_id);
        if(order != null){
            return order;
        }
        return null;
    }

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

    public List<Order> findByoNum(String order_num,String paymentTime){
        List<Order> list = orderMapper.findByoNum(order_num);
        orderMapper.updatePaymentTime(order_num,paymentTime);
        if(list != null){
            return list;
        }
        return null;
    }

    public int insertOrder(Order order){
        orderMapper.insertOrder(order);
        return 1;
    }

    public int updateSeat(Integer seat_id,Integer order_id){
        orderMapper.updateSeat(seat_id,order_id);
        return 1;
    }

    public List<String> scheduleTable(){
        return orderMapper.scheduleTable();
    }

    public void updateStatus(String orderTime){
        orderMapper.updateStatus(orderTime);
    }



    public Order searchByNum(String order_num){
        Order order = orderMapper.searchByNum(order_num);
        if(order != null){
            return order;
        }
        return null;
    }

    //在改签时调用（返回order类型）
    public Order searchById(Integer order_id){
        Order order = orderMapper.searchById(order_id);
        if(order != null){
            return order;
        }
        return null;
    }

    //在改签时调用
    public List<Order> searchByID(Integer order_id){
        List<Order> list = orderMapper.searchByID(order_id);
        if(list != null){
            return list;
        }
        return null;
    }

    //在改签支付完成后调用
    public List<Order> searchByID(Integer order_id,String paymentTime){
        List<Order> list = orderMapper.searchByID(order_id);
        orderMapper.updatePaymentTIME(order_id,paymentTime);
        if(list != null){
            return list;
        }
        return null;
    }

    //在改签时调用
    public int updateChange(String change0,String order_num,Integer order_id){
        orderMapper.updateChange(change0,order_num,order_id);
        return 1;
    }



    public void ReturnTicket(Integer order_id){orderMapper.ReturnTicket(order_id);}

    public Order selectById(Integer order_id) {
        return orderMapper.searchById(order_id);
    }

    public int updateAfterChange(String change, String order_num, Integer order_id, String realPrice) {
        return orderMapper.updateAfterChange(change,order_num,order_id,realPrice);
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
