package com.example.demo2.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentBO {
    //省略其他的业务参数，如商品id、购买数量等


    private String subject;

    //总金额
    private BigDecimal total = BigDecimal.ZERO;
}

