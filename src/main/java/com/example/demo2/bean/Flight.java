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

    private Integer arrival_airport;

    private Integer departure_airport;

    private String passby_city;

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

    public Integer getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(Integer arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public Integer getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(Integer departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getPassby_city() {
        return passby_city;
    }

    public void setPassby_city(String passby_city) {
        this.passby_city = passby_city == null ? null : passby_city.trim();
    }




}
