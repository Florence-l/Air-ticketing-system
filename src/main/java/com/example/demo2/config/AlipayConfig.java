package com.example.demo2.config;

import java.io.FileWriter;
import java.io.IOException;

//支付宝接口设置
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000117669434";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCRBWwmbPKYl0tnzZujLVbP0PY90fwInGnKjGFflSAYY+DRhm2liUB5DIGXtCYeOslitpDvh5Q4sZuVI3LQbQmHZQuheEBQ0xb3bcUaX8eEq8Qp+/GsrKQ/5WviQt9LevL0j5lxyOi+WLmTojdqQKHOlbsoiBxzlmfHqV4Emy/vIAXYDyu3VNqIhYW8K6B8Wit4hXRhfJeSvWg7GTHPiG90h2ao8JxpNWChYY4weXB9ljWV3vGJ5C0ZMAAFMCjvVQTROiuy1+TZeBjaGYPkYDRUNhAhoF9kjhGTQ0exucg0JG4qNsAz4NcFpQJCW1R3X8h9iQ0DyNB2s1LzzJ1VumjrAgMBAAECggEADDpe0iahlgg7zXbixrGN/lvqR3ArRjaaHQAVPc//Ms9JJ6GkIS8fhpVtX/NKu4XNcUtfkTLnZphjPAIPLk/jW81DW8wd8DK8XwSceArXcfJhygB6ckrkVpbrr9pfpaeqUMDC+XstVM96Zl5pOcZVXmv1qgdADucBvS4kxVKoigsSWrTtAQA1SuVILdAY+RqQ4rN4H2shC44QxTURTXMgyBv+0VoT7JVT41fQ1Ce2o+3eh1Uh/QyfQBsxaBycudl7uTJ4xHCWqkj51cA/wPTWyxFSN10uin0ZxQ5DTzbvrKkI3rtpVzKomWdoWWFTACT7LmoHlBQ8Ac302YerWZoIMQKBgQDfMJwiw45O6jQ5WfKmMXGfj6f4DbO5VsNGMc4C6RhDlOphM1sQ2NWOmplsziNGx2rYRtcJx7Nk19VO8sU24QyN1XG3caOsX39x1EGykyIfMMGcFLCiTtuaw55lF29kgV/RQQnD0gyiSGOWp4TQvG5GcQfDMPWruFMLyYPuLR7s9wKBgQCmVw9alTakTj0klbmLJWfCbGp3GPwVbHnLKVvuBOap6rIabdo8CYGd7KrYGNZzS2jdZFtTCs1aDqcPbMDjfOakoUCIsFMdkQ756dujrSBx+yVErFPO465gGplJYQULO8TR60sv9iqRY6js0bQfaWjgTgwVMQ+8C0eaHQKwxHpqrQKBgQCHWpKGHrSyNSTg1yTqFzhCPTvvmlexgeXBLzkx2K0HZ0tREXUb3XR+HFIJg2YvaYbpjOSioMKx2JcbXu2tX4Rei+CAEOHjCrNelAwXwVVDNy1je8S3Wj2iORpZUoISoMc8P0YtxLg2vtHurr9EW9JA1BGSFaIXtxxeHKLvEdwcdwKBgDtLmZuOyVW01jLudJ/Xi69Q+nrOGz5/1sLHUY/9i0RS935YXo08Tko/jX9PC1M6NH2HQs7NcPqDcqYa/H+bjJRvLxQK1vYZOZ947+Te2EwJXPWoAJAltYlose/od+XTPoweNkU4mWAOiK8y6eSwrMQYzlHXlWZRFwsnAEiHIaRZAoGBAM/Uz0tS84evZmzeJzeu1cqJlTfeMzBkkeK2mnvXNd7ig7vXQdezlSQRBE0MOrb96DBgR3+ws55JtLbWmzo1Rngll4DZUJ/4R2ykM133gVcrwGSGhdTzRYMKVEj2L9D33U1LIMq3rSiIki1lI+M0XGUMQ3zRWVAIUdIFMvkcCYNz";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgTPFbaPAa01jtOuSm765qNAq4dUez5X3d/bsQYd1Mbh0HZPJloEinuyZY7kvWDuKbdzDBzybpDVZc6CDSHNqPQKn1bhyTCY0nNrIbiq6lhVr/njI7rCL4039uoHieLJsMaOhfaTwv+a5aiZfDlxiKxFe4P6omB4G5vstAG2I5SrPWNeMKWswI5V9cfvzZ42aoIsI5T24QOFBEEEAde4lGx7srywc83ISbYY7v7LAc2V2dJfRhzsGzRxvz89jNgWLSEE93QtTh90G29VLZ0y52sOiMN+vujVkP7fszHvCPgHqSeYnc1wf8SG0+hwd9aQeA0racK7R/nHocLgGhBWIWwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://47.115.202.133:8080/payNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://47.115.202.133:8080/payReturn";
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
