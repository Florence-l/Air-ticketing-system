package com.example.demo2.bean;

import javax.persistence.Id;

public class Passenger {
    private String user_name;
    @Id
    private String passenger_id;
    private String user_tel;
    private String user_id;

    public Passenger(){

    }

    public Passenger(String user_name,String passenger_id,String user_tel){
        this.user_name = user_name;
        this.passenger_id = passenger_id;
        this.user_tel = user_tel;
    }

    public Passenger(String user_name,String passenger_id,String user_tel,String user_id){
        this.user_name = user_name;
        this.passenger_id = passenger_id;
        this.user_tel = user_tel;
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(String passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this. user_tel =  user_tel;
    }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) {this.user_id = user_id; }


}
