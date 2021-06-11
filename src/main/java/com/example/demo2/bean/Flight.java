package com.example.demo2.bean;

import java.util.Date;

public class Flight {
    private Integer flight_id;

    private Integer airplane_id;

    private String departurecity;

    private String arrivalcity;

    private String departuretime;

    private String arrivaltime;

    private String date;

    private String arrival_airport;

    private String departure_airport;

    private String passby_city;

    private Integer price;

    private String seat_status;

    private Integer type;

    private Integer bc_remaining;

    private Integer ec_remaining;

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public Integer getAirplane_id() {
        return airplane_id;
    }

    public void setAirplane_id(Integer airplane_id) {
        this.airplane_id = airplane_id;
    }

    public String getDeparturecity() {
        return departurecity;
    }

    public void setDeparturecity(String departurecity) {
        this.departurecity = departurecity == null ? null : departurecity.trim();
    }

    public String getArrivalcity() {
        return arrivalcity;
    }

    public void setArrivalcity(String arrivalcity) {
        this.arrivalcity = arrivalcity == null ? null : arrivalcity.trim();
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getPassby_city() {
        return passby_city;
    }

    public void setPassby_city(String passby_city) {
        this.passby_city = passby_city == null ? null : passby_city.trim();
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBc_remaining() {
        return bc_remaining;
    }

    public void setBc_remaining(Integer bc_remaining) {
        this.bc_remaining = bc_remaining;
    }

    public Integer getEc_remaining() {
        return ec_remaining;
    }

    public void setEc_remaining(Integer ec_remaining) {
        this.ec_remaining = ec_remaining;
    }
}
