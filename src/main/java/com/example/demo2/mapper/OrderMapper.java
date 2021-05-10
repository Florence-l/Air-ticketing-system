package com.example.demo2.mapper;

import com.example.demo2.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findByid(@Param("user_id") Integer user_id,@Param("page") int page,@Param("limits") int limits);

    int countAllOrder();

    //    int insertOrder(Order order);
//
//    int deleteOrder();
//    List<Order> findUnpayByid(Integer userid);

}
