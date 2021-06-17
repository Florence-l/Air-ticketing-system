package com.example.demo2.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.example.demo2.Util.OrderUtil;
import com.example.demo2.bean.PaymentBO;
import com.example.demo2.config.AlipayConfig;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@Service
public class PayService {
    /**
     *  下单支付
     * */
    public void pay(String totalPrice, String subject, String order_no,HttpServletResponse response)throws IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        String body="";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + order_no + "\","
                + "\"total_amount\":\"" + totalPrice + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"5m\"," //支付有效时间
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
            System.out.println("form==>" + form);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     *可以部分退款及全额退款
     * @param order_no 订单号
     * @param refund_amount 退款金额
     * @param type 0未改签过 1改签过（补差价） 2改签过（退差价）
     * @return success退款成功  msg失败信息
     * @throws AlipayApiException
     */
    public String refund(String order_no,String refund_amount,int type) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.APP_ID,
                AlipayConfig.APP_PRIVATE_KEY,
                "json",
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.sign_type
        );
        AlipayTradeRefundModel model;
        AlipayTradeRefundRequest refundRequest;
        AlipayTradeRefundResponse response;

        //之前没有改签过,直接退款或退差价
        if(type== 0){
            model = new AlipayTradeRefundModel();
            model.setOutTradeNo(order_no); //与预授权转支付商户订单号相同，代表对该笔交易退款
            model.setRefundAmount(refund_amount);
            model.setOutRequestNo(OrderUtil.getOrderNo());//标识一次退款请求，同一笔交易多次退款需要保证唯一，如部分退款则此参数必传。
            refundRequest = new AlipayTradeRefundRequest();
            refundRequest.setBizModel(model);
            response = alipayClient.execute(refundRequest);
            if(response.isSuccess()){
                return "success";
            }
            else{
                return response.getMsg();
            }
        }
        //改签过(补差价)
        else if(type== 1){
            //查询订单，取出差价金额
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            request.setBizContent("{\"out_trade_no\":\"" + order_no + "\"}");
            AlipayTradeQueryResponse queryResponse = alipayClient.execute(request);
            String diff_amount=queryResponse.getTotalAmount();
            float diff_amount_f=Float.valueOf(diff_amount);
            float refund=Float.valueOf(refund_amount);
            //差价金额小于实际退款金额，先从差价订单里退款，再从原订单退款
            if(refund>diff_amount_f){
                System.out.printf("\nrefund>diff_amount_f");
                //先从差价订单里退款
                model = new AlipayTradeRefundModel();
                model.setOutTradeNo(order_no);
                model.setRefundAmount(diff_amount);
                refundRequest = new AlipayTradeRefundRequest();
                refundRequest.setBizModel(model);
                response= alipayClient.execute(refundRequest);
                if(!response.isSuccess()) {
                    return response.getMsg();
                } else {
                    System.out.printf("\nsuccess 退款:"+response.getRefundFee());
                }

                //再从原订单退款
                order_no=order_no.substring(0,order_no.length()-4);//获取原订单的订单号
                model = new AlipayTradeRefundModel();
                model.setOutTradeNo(order_no);
                model.setRefundAmount(String.valueOf(refund-diff_amount_f));//剩下的金额从原订单扣
                model.setOutRequestNo(OrderUtil.getOrderNo());
                refundRequest = new AlipayTradeRefundRequest();
                refundRequest.setBizModel(model);
                response = alipayClient.execute(refundRequest);
                if(!response.isSuccess()) {
                    return response.getMsg();
                } else {
                    System.out.printf("\nsuccess 退款:"+response.getRefundFee());
                }
            }
            else{//差价金额大于等于实际退款金额，直接从差价订单里扣款
                System.out.printf("\nrefund<=diff_amount_f");
                model = new AlipayTradeRefundModel();
                model.setOutTradeNo(order_no);
                model.setRefundAmount(refund_amount);
                model.setOutRequestNo(OrderUtil.getOrderNo());
                refundRequest = new AlipayTradeRefundRequest();
                refundRequest.setBizModel(model);
                response = alipayClient.execute(refundRequest);
                if(!response.isSuccess()) {
                    return response.getMsg();
                } else {
                    System.out.printf("\nsuccess 退款:"+response.getRefundFee());
                }
            }
        }
        //之前改签过(退差价)
        else{
            //直接退款
            order_no=order_no.substring(0,order_no.length()-4);//获取原订单的订单号
            System.out.printf("\n"+order_no);
            model = new AlipayTradeRefundModel();
            model.setOutTradeNo(order_no);
            model.setRefundAmount(refund_amount);//从原订单里退款
            model.setOutRequestNo(OrderUtil.getOrderNo());
            refundRequest = new AlipayTradeRefundRequest();
            refundRequest.setBizModel(model);
            response = alipayClient.execute(refundRequest);
            if(!response.isSuccess()) {
                return response.getMsg();
            } else {
                System.out.printf("\nsuccess 退款:"+response.getRefundFee());
            }
        }
        return "success";
    }
}
