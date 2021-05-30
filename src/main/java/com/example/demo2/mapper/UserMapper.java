package com.example.demo2.mapper;

import com.example.demo2.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);

    User loadUserByUsername(String username);

    User selectUserByEmail(String email);

    User selectUserByName(String username);

    void updateInfo(String birthday,Integer sex,Integer userId);

    void resetPwd(User u);
}