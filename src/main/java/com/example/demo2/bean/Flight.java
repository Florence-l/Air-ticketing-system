package com.example.demo2.bean;

import java.time.LocalTime;
import java.util.Date;

public class Flight {
    private Integer flightId;
    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Date date;
    private Integer arrivalAirport;
    private Integer departureAirport;
    private String passbyCity;


    public Integer getFlightId(){ return flightId; }

    public void setFlightId(Integer flightId){ this.flightId = flightId;}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Date getDate(){return date;}

    public void setDate(Date date){this.date = date;}

    public Integer getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Integer arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Integer getDepartureAirport(){
        return departureAirport;
    }

    public void setDepartureAirport(Integer departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getPassbyCity() {
        return passbyCity;
    }

    public void setPassbyCity(String passbyCity) {
        this.passbyCity = passbyCity;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "flightid=" + flightId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", date=" + date +
                ", depatureAirport=" + departureAirport +
                ", arrivalAirport=" + arrivalAirport +
                ", passbyCity=" + passbyCity +
                '}';
    }

}
