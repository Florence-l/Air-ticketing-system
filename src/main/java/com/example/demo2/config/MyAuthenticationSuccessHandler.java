package com.example.demo2.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)throws ServletException, IOException {
        if(request.getHeader("X-Requested-With")!=null){
            response.setContentType("text/json;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.write("{\"status\":\"true\"}");
            out.flush();
            out.close();
        }
        else {
            response.setContentType("application/html;charset=UTF-8");
            //登录成功后返回上一次请求的页面
            RequestCache cache = new HttpSessionRequestCache();
            SavedRequest savedRequest = cache.getRequest(request, response);
            String url = null;
            if (savedRequest != null)
                url = savedRequest.getRedirectUrl();
            //没有请求页面就跳转到首页
            if (url == null)
                getRedirectStrategy().sendRedirect(request, response, "/index");
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
