package com.example.demo2.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录失败后跳转回登录页面
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    public MyAuthenticationFailureHandler(){
        this.setDefaultFailureUrl("/login");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception)throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            super.onAuthenticationFailure(request,response,exception);
    }
}
