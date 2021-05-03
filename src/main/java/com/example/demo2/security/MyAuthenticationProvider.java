package com.example.demo2.security;

import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserService userDetailsService;
    /**
     * 进行身份认证
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取用户名和密码
        String name=authentication.getName();
        String password=authentication.getCredentials().toString();
        //获取封装用户信息的对象
        UserDetails user=userDetailsService.loadUserByUsername(name);
        if(user==null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        /*match  解密  @param 明文 加密后密码*/
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean flag=passwordEncoder.matches(password,user.getPassword());
        if(flag) return new UsernamePasswordAuthenticationToken(user,password,user.getAuthorities());
        else throw new BadCredentialsException("密码错误");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
