package com.example.demo2.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private Integer sex;
    private String birthday;
    private Boolean enabled;
    private Boolean locked;
    private Boolean expired;
    private Boolean credentialsexpire;

    public User(String username, String password) {
            this.username=username;
            this.password=password;
    }

    public User(String username, String password, String email) {
        this.username=username;
        this.password=password;
        this.email=email;
    }

    public User(String username,String email,Integer sex,String birthday){
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public User() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getSex(){return sex;}

    public void setSex(Integer sex) {this.sex = sex;}

    public String getBirthday(){return birthday;}

    public void setBirthday(String birthday) {this.birthday = birthday;}

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsexpire;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority>authorities=new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public void setCredentialsexpire(Boolean credentialsexpire) {
        this.credentialsexpire = credentialsexpire;
    }
}