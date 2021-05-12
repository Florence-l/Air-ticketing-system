package com.example.demo2.bean;




//import java.util.Date;

public class Order {
    private Integer order_id;
    private Integer user_id;
    private Integer passenger_id;
    private Integer flight_id;
    private String orderTime;
    private Integer paymentStatus;
    private String paymentTime;
    private Integer seat_id;
    private Float realPrice;
    private Flight flight;

    public Order(){

    }

    public Order(Integer order_id,Integer user_id,Integer passenger_id,
                 Integer flight_id,String orderTime,Integer paymentStatus,String paymentTime,
                 Integer seat_id, Float realPrice){
        this.order_id = order_id;
        this.user_id = user_id;
        this.passenger_id = passenger_id;
        this.flight_id = flight_id;
        this.orderTime = orderTime;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
        this.seat_id = seat_id;
        this.realPrice = realPrice;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Integer passenger_id) {
        this.passenger_id = passenger_id;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public String getOrderTime() {
        return  orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(Integer seat_id) {
        this.seat_id = seat_id;
    }

    public Float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Float realPrice) {
        this.realPrice = realPrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }



    @Override
    public String toString() {
        return "Orderdetail{" +
                "flightid=" + flight_id +
                "orderTime=" + orderTime +
                "seatid=" + seat_id +
                "realPrice" + realPrice +
                '}';
    }
}

