package com.example.demo2.config;

import com.example.demo2.security.MyAuthenticationProvider;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;

    @Bean
    PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Resource
    private SessionRegistry sessionRegistry;

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    //记住我功能实现
    @Autowired
    DataSource dataSource;
    @Bean
    public RememberMeServices rememberMeServices() {
        JdbcTokenRepositoryImpl rememberMeTokenRepository = new JdbcTokenRepositoryImpl();
        rememberMeTokenRepository.setDataSource(dataSource);
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices("INTERNAL_SECRET_KEY", userService, rememberMeTokenRepository);
        rememberMeServices.setParameter("remember-me");
        return rememberMeServices;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //防止重复登录
        http
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/login")
                .sessionRegistry(sessionRegistry);

        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureHandler(myAuthenticationFailureHandler)
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers("/","/register","/login","/loginModal","/index","/sendEmail","/reset","/result","/ifAuthentication","/flight").permitAll()
                .antMatchers("/css/**","/images/**","/*.css","/js/*","/*.js","/index/re","/index/pr","/flight","/layui/**/**","/layui/font/**","/font/**").permitAll() // 在这里添加
                .anyRequest().authenticated()

                //记住我功能
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .tokenValiditySeconds(2 * 24 * 60 * 60)
                .key("INTERNAL_SECRET_KEY")

                //登出功能
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll()

//                .and()
//                .headers().frameOptions().disable()

                .and()
                .csrf().disable();

        //防止iframe拦截
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态文件拦截的问题
        web.ignoring().antMatchers("/css/**","/**/*.css","/js/*","/*.js","/layui/**/**","/**/*.jpg");
    }

}