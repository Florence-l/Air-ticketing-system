package com.example.demo2.bean;

//import java.util.Date;

public class Order {
    private Integer order_id;
    private Integer user_id;
    private String user_name;
    private String passenger_id;
    private Integer flight_id;
    private String orderTime;
    private Integer paymentStatus;
    private String paymentTime;
    private Integer seat_id;
    private String seat_type;
    private String realPrice;
    private String order_num;
    private Integer is_valid;
    private Flight flight;
    private String change0;

    public Order(){

    }

    public Order(Integer order_id,Integer user_id,String passenger_id,
                 Integer flight_id,String orderTime,Integer paymentStatus,String paymentTime,
                 Integer seat_id, String seat_type, String realPrice,String order_num, String change0){
        this.order_id = order_id;
        this.user_id = user_id;
        this.passenger_id = passenger_id;
        this.flight_id = flight_id;
        this.orderTime = orderTime;
        this.paymentStatus = paymentStatus;
        this.paymentTime = paymentTime;
        this.seat_id = seat_id;
        this.seat_type = seat_type;
        this.realPrice = realPrice;
        this.order_num = order_num;
        this.change0 = change0;
    }

    public Order(Integer userid, String user_name, String passenger_id,
                 Integer flght_id, String seat_type,String orderTime, Integer paymentStatus,
                 String realPrice, String order_num, String change0) {
        this.user_id = userid;
        this.user_name = user_name;
        this.passenger_id = passenger_id;
        this.flight_id = flght_id;
        this.seat_type = seat_type;
        this.orderTime = orderTime;
        this.paymentStatus = paymentStatus;
        this.realPrice = realPrice;
        this.order_num = order_num;
        this.change0 = change0;
    }

    public Order(Integer order_id) {
        this.order_id=order_id;
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

    public String getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(String passenger_id) {
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

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getOrder_num(){
        return order_num;
    }

    public void setOrder_num(String order_num){
        this.order_num = order_num;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public String getUser_name(){
        return user_name;
    }

    public void setIs_valid(Integer is_valid){this.is_valid = is_valid;}

    public Integer getIs_valid(){return is_valid;}

    public String getChange() {
        return change0;
    }

    public void setChange(String change0) {
        this.change0 = change0;
    }


    @Override
    public String toString() {
        return "Orderdetail{" +
                "order_id" + order_id+
                "user_id" + user_id+
                "user_name" + user_name+
                "passenger_id"+passenger_id+
                "flightid=" + flight_id +
                "orderTime=" + orderTime +
                "paymentStatus" + paymentStatus +
                "paymentTime" + paymentTime+
                "seatid=" + seat_id +
                "seat_type=" + seat_type +
                "realPrice" + realPrice +
                "order_num" + order_num+
                "is_valid" + is_valid +
                "change" + change0 +
                '}';
    }
}


