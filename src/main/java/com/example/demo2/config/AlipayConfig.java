package com.example.demo2.config;

import java.io.FileWriter;
import java.io.IOException;

//支付宝接口设置
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000117669434";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCnqCxhZUK6UvbV61kvmecq+0MnU8VtBYpG7HxWOnPbBgO2R7GMhbhyho3Dw18l7oei7CGlJSb64503iz6/KvG7PPfG3qUcUY5dm6x/ixaED128OpL57tQW30B2zAMn4WmMWGRgxlXwsiz9KEkUMtEkY4fOlR/eWsDbj2XFhZLTrb1WF42wo2l/erndJJufYgD8JdRUxft2mJyqCpWFO5U+oaLGvkjARab+PxbfDikKnciOlEEsYSqYGlaOW5HsVX7nlRiJzcZfsUBy1LbiasKBP/7g+jNC3nEuXTU+HMr1CWnnIxQu8j63YRsNNwHpDtcbN+Fa1I9263/MMPo4uJQBAgMBAAECggEBAJwrBJv8bLmOSipdPQgf/bqFrbnWSFITFQiXhAH2FLDx20dRZMsWTAcx2p99PwXgOCvEdFqWTsboeiug4uPDJsRYOBzgiz/joA8F0RpE/sYoYhHRJdL1ak2byzpdMptVVrHKLEYQuUCpV0rA2cGczD9vDrOZKMRAGODro8rnbysIaxstGLDxuYK/MZ45thtlWJFNryi6gRkpWrNhGOsBzi2BUmuDTpx34z7I/juUsbU68wuv7ujSJ92AtL3/esRuevK/ZnK74MRtWs016lw7Eu+2SMFbSvFHTMmWBkm5Isx700h5/wue2vSKeFGgU47aYztyljWmfIxcKGkr4gaga1kCgYEA0MYej+vSgCaTBBavffjQeYzySvLl/rtoj40A9xjZxY6kb4pmWe3jYwP8HelKezPc0ZVEdYOlMs4Mw5O2B6GnzAmHea9tw61CLNBa3SLTaHmPO6lk9Sl4FxT3Wl9/2XasptYU7N8Nh1gT3Eh6gLpNbHqQ0t7KIiOWyoRhFhyHx3cCgYEAzZUDLqPv0xPXttwOn7R+TlC8/b5T2yJBTdm7VzMUCFZB5BU+b393ek53n1/zJQ2+zt9VK0+gAsLUTk1rDboDJBAsVEHnfV/FG0osY5LDsqdGgzmUM4mVuwI2ZI22nTaKdLrL+zBmdEch6epmcqSPrBKq9UFNhe4FGk5gwbiGTkcCgYEAv7zEws8FI3bJ3J5tDBegd5Gv0I+mFT3O+M+6DDXBg0PO1zXXbE4jYIN/X4UrR11JNx6MohSGetNsO46ERhv8PFKgHmy0NVsBBKFSYfejCzdhCZ04QESWqzQxO8mrZHAufobs7uKnzR5iFxRllaYEh1VFfrToFVR0H4hWlfUb6l8CgYEAmvPnCx+Lo0hAflaNOzj7PqJCYOs1H2VJXZlwSEB6p/IEBhsxDS9BadF8/oZ+qP4cRKc29cc1bv9UsJRPzW/X8DW0g+mMxwYJdhFJNeAoWx8T2dLrbpEUZ3k6cD4UV5kFcQheuTz47Em1OS9w+pYoqGUFsIgBG6KU/Af5i+o+aP8CgYB9qNQzYt56A5yS4exwBmhh8s7VEcAWv4KHnZdg1E8vyTF2wjkIIoa9nh3iYvkyZ9YcuU3e5xyrMq+INKD8RMG9H/2hBaZCOqo1r4j8gH9uFnDS7xpVsYaM/ROFkBviNLmy8IGADTCn9EpLDrbOTKak/E1dujCrMIvLawngJaJiSw==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgTPFbaPAa01jtOuSm765qNAq4dUez5X3d/bsQYd1Mbh0HZPJloEinuyZY7kvWDuKbdzDBzybpDVZc6CDSHNqPQKn1bhyTCY0nNrIbiq6lhVr/njI7rCL4039uoHieLJsMaOhfaTwv+a5aiZfDlxiKxFe4P6omB4G5vstAG2I5SrPWNeMKWswI5V9cfvzZ42aoIsI5T24QOFBEEEAde4lGx7srywc83ISbYY7v7LAc2V2dJfRhzsGzRxvz89jNgWLSEE93QtTh90G29VLZ0y52sOiMN+vujVkP7fszHvCPgHqSeYnc1wf8SG0+hwd9aQeA0racK7R/nHocLgGhBWIWwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/payNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/payReturn";
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
