package com.example.demo2.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.demo2.bean.Flight;
import com.example.demo2.bean.Order;
import com.example.demo2.config.AlipayConfig;
import com.example.demo2.service.FlightService;
import com.example.demo2.service.OrderService;
import com.example.demo2.service.PayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.InsufficientResourcesException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
public class PayController {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FlightService flightService;

    private String order_num_;
    private Integer order_id;
    private Order order1;
    public String gseat_status;
    public Integer gseat_id;
    public String gseat_type;
    public Integer gflight_id;

    /**
     * web端订单支付
     * @param totalPrice
     * @param subject
     * @param response
     * @throws IOException
     */
    @RequestMapping("/pay")
    @ResponseBody
    public void payController(String totalPrice,String subject,String order_num,String change,String order_id,String book_flight_id,HttpServletResponse response)throws IOException{
        order_num_ = order_num;
        //改签时使用
        if(order_id!=null) {
            Flight flight= flightService.findById(book_flight_id);
            order1=orderService.selectById(Integer.valueOf(order_id));
            String order_num_=order_num+(order1.getPassenger_id()).substring(order1.getPassenger_id().length()-4);
            gseat_id = order1.getSeat_id();
            gseat_type = order1.getSeat_type();
            gflight_id = order1.getFlight_id();
            System.out.println(order1);
            if(gseat_id!=null){
                gseat_status=flightService.findSeatId(gflight_id);
                deleteSeat(gseat_status,gseat_id);

            }


            order1.setOrder_num(order_num_);
            order1.setFlight(flight);
            order1.setFlight_id(flight.getFlight_id());

            order1.setSeat_id(null);
            String price=order1.getRealPrice();
            order1.setRealPrice(String.valueOf(Float.parseFloat(totalPrice)+Float.parseFloat(price)));//改签后的价格
            if (change != null) {
                order1.setChange(change);

            } else {
                order1.setChange("0");
            }
            payService.pay(totalPrice, subject,order_num_, response);
        }

        else {
            order1=null;
            payService.pay(totalPrice, subject, order_num, response);
        }
    }

    @RequestMapping("/getOd_num")
    @ResponseBody()
    public String getOd_num(){
        System.out.printf("\n order_num="+order_num_);
        return String.valueOf(order_num_);
    }


    @RequestMapping("/getOd_id")
    @ResponseBody()
    public Integer getOd_id(){
        return order_id;
    }

    //同步通知
    @RequestMapping("/payReturn")
    public String returnCall(HttpServletRequest request) throws AlipayApiException {
        System.out.println("支付成功, 进入同步通知接口...");
        if(order1!=null) {
            System.out.printf("\n退款金额："+String.valueOf(Float.parseFloat(order1.getRealPrice())+50));
            //改签后的价格
            order1.setRealPrice(String.valueOf(Float.parseFloat(order1.getRealPrice())));
            //更新数据库
            if(gseat_type=="1"){
                flightService.deleteBC(gflight_id);
            }
            else{
                flightService.deleteEC(gflight_id);
            }
            order_num_ = order1.getOrder_num();
            flightService.updateSeatStatus(gflight_id,gseat_status);


            orderService.updateAfterChange(order1.getFlight_id(),order1.getSeat_id(),order1.getChange(), order1.getOrder_num(), order1.getOrder_id(),order1.getRealPrice());
            return "orderDetail";
        }
        else{
            return "orderDetail";
        }
    }

    //异步通知
    @RequestMapping("/payNotify")
    @ResponseBody
    public String notify(HttpServletRequest request) throws Exception {
        System.out.println("支付成功, 进入异步通知接口...");
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");

            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.CHARSET,
                AlipayConfig.sign_type);

        if (signVerified) {
            // 异步通知返回的参数（部分说明）
            // out_trade_no ：商户订单号，商户网站订单系统中唯一订单号
            // trade_no ：支付宝交易号，支付宝交易凭证号
            // app_id ：开发者的app_id
            // out_biz_no ：商户业务号
            // buyer_id ：买家支付宝用户号，买家支付宝账号对应的支付宝唯一用户号，以 2088 开头的纯 16 位数字
            // seller_id ：卖家支付宝用户号 卖家支付宝用户号
            // trade_status ：交易状态
            // total_amount ：订单金额
            // receipt_amount ：实收金额
            // invoice_amount ：开票金额
            // buyer_pay_amount ：付款金额
            // point_amount ：集分宝金额 使用集分宝支付的金额，单位为元，精确到小数点后2位
            // subject ：订单标题
            // body ：商品描述
            // fund_bill_list ：支付金额信息
            // passback_params  回传参数
            System.out.println("********************** 支付宝异步通知成功   **********************");
            System.out.println("异步通知返回参数：" + params.toString());
            System.out.println("********************** 支付宝异步通知成功   **********************");

            if (params.get("trade_status").equals("TRADE_FINISHED")){
                // 注意： 注意这里用于退款功能的实现
                System.out.println("执行退款相关业务");

                //交易成功
            }else if (params.get("trade_status").equals("TRADE_SUCCESS")) {
                // 1. 根据out_trade_no 查询订单
                // 2. 判断total_amount 是否正确，即是否为商户订单创建时的金额
                // 3. 校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
                // 4. 验证app_id是否为该商户本身
                // 5. 判断该笔订单是否在商户网站中已经做过处理
                // 6. 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序，修改订单状态
                // 7. 如果有做过处理，不执行商户的业务程序
                return "orderDetail";
            }

        } else {
            System.out.println("支付, 验签失败...");
            return "booking";
        }
        return "orderDetail";
    }

    public void deleteSeat(String seat, int id){
        char[] seatstatus=seat.toCharArray();
        seatstatus[id-1]='0';
        seat= Arrays.toString(seatstatus).replaceAll("[\\[\\]\\s,]", "");
        gseat_status=seat;
    }

}
