package com.example.demo2.mapper;

import com.example.demo2.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findByid(@Param("user_id") Integer user_id,@Param("page") int page,@Param("limits") int limits);

    int countAllOrder();

    List<Order> findUnpay(@Param("user_id") Integer user_id,@Param("page") int page,@Param("limits") int limits);

    int insertOrder(Order order);

    Order findByNum(@Param("order_num") String order_num,@Param("passenger_id") String passenger_id);

    int updateSeat(@Param("seat_id") Integer seat_id,@Param("order_id")Integer order_id );

    List<Order> findByoNum(@Param("order_num") String order_num);

    Order findById(@Param("order_id") Integer order_id);

    List<String> scheduleTable();

    void updateStatus(@Param("orderTime") String orderTime);

    void updatePaymentTime(@Param("order_num") String order_num,@Param("paymentTime") String paymentTime);

}
