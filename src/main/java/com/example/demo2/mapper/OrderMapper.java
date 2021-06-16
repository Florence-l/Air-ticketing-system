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

    List<String> scheduleTable();

    void updateStatus(@Param("orderTime") String orderTime);

    void updatePaymentTime(@Param("order_num") String order_num,@Param("paymentTime") String paymentTime);



    Order searchByNum(@Param("order_num") String order_num);

    Order searchById(@Param("order_id") Integer order_id);

    List<Order> searchByID(@Param("order_id") Integer order_id);

    void updatePaymentTIME(@Param("order_id") Integer order_id,@Param("paymentTime") String paymentTime);

    int updateChange(@Param("change0")String change0, @Param("order_num")String order_num, @Param("order_id")Integer order_id);


    void ReturnTicket(@Param("order_id") Integer order_id);

}
