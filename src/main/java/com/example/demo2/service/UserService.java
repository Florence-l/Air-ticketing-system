package com.example.demo2.service;

import com.example.demo2.bean.User;
import com.example.demo2.mapper.UserMapper;
import com.example.demo2.security.MD5Utils;
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
import java.text.SimpleDateFormat;
import java.util.*;

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
     * 判断邮箱是否已经注册
     * 用户已存在 3
     * 邮箱已注册 2
     * 用户不存在 邮箱未注册 1
     */
    public int register(User user) {
        //判断用户是否已存在
        User u=userMapper.loadUserByUsername(user.getUsername());
        //不存在，添加用户
        if(u==null) {
            u=userMapper.selectUserByEmail(user.getEmail());
            //该邮箱还没注册
            if(u==null) {
                //使用BCryptPasswordEncoder进行密码加密
                String password = passwordEncoder().encode(user.getPassword());
                user.setPassword(password);
                userMapper.insert(user);
                return 1;
            }
            else
                return 2;
        }
        else return 3;
    }

    public User selectUserByEmail(String email) {
        User u = userMapper.selectUserByEmail(email);
        return u;
    }

    public User selectUserByName(String name){
        User u = userMapper.loadUserByUsername(name);
        return u;
    }

    public void resetPwd(User u) {
        userMapper.resetPwd(u);
    }

    //生成随机验证码
    public String VerifyCode(int n){
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < n;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        return sb.toString();
    }

    //保存验证码和时间
    public Map<String, Object> saveCode(String code){
        Map<String, Object> resultMap=new HashMap<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期

        String hash =  MD5Utils.code(code);//生成MD5值
        resultMap.put("hash", hash);
        resultMap.put("tamp", currentTime);
        return resultMap;
    }

    public void insertInfo(String birthday,Integer sex,Integer userId){
        userMapper.updateInfo(birthday,sex,userId);
    }
    public void insertImage(String image,Integer userId){
        userMapper.updateImage(image,userId);
    }

}
