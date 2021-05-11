package com.example.demo2.bean;

import java.util.Date;

public class Flight {
    private Integer flightId;

    private Integer airplaneId;

    private String departurecity;

    private String arrivalcity;

    private Date departuretime;

    private Date arrivaltime;

    private Date date;

    private Integer arrivalAirport;

    private Integer departureAirport;

    private String passbyCity;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
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

    public Date getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(Date departuretime) {
        this.departuretime = departuretime;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Integer arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Integer getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Integer departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getPassbyCity() {
        return passbyCity;
    }

    public void setPassbyCity(String passbyCity) {
        this.passbyCity = passbyCity == null ? null : passbyCity.trim();
    }
}