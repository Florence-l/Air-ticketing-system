package com.example.demo2.bean;

import javax.persistence.Id;


public class Passenger {
    private String user_name;
    @Id
    private Integer passenger_id;
    private Integer user_tel;

    public Passenger(){

    }

    public Passenger(String user_name,Integer passenger_id,Integer user_tel){
        this.user_name = user_name;
        this.passenger_id = passenger_id;
        this.user_tel = user_tel;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Integer passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Integer getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(Integer user_tel) {
        this. user_tel =  user_tel;
    }

}
