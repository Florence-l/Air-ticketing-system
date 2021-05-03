package com.example.demo2.service;

import com.example.demo2.bean.User;
import com.example.demo2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class UserService  implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 实现loadUserByName方法，
     * 通过用户名在数据库中查询，
     * 将查询到的用户封装到Security的User类并返回
     * */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //在数据库中查找用户
        User user = userMapper.loadUserByUsername(name);
        if (user == null)
            throw new BadCredentialsException("用户不存在");
        else
            System.out.println("用户名"+user.getUsername()+"   密码"+user.getPassword());
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        //对用户信息进行封装，返回给Provider，进行身份认证
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    /**注册
     * 判断用户中是否已经存在待注册用户名，不存在就将密码加密后插入到数据库表中
     */
    public boolean register(User user) {
        //判断用户是否已存在
        User u=userMapper.loadUserByUsername(user.getUsername());
        //不存在，添加用户
        if(u==null) {
            //使用BCryptPasswordEncoder进行密码加密
            String password = passwordEncoder().encode(user.getPassword());
            user.setPassword(password);
            userMapper.insert(user);
            return true;
        }
        else return false;
    }
}
