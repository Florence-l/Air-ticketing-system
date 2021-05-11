package com.example.demo2.controller;

import com.example.demo2.bean.User;
import com.example.demo2.security.MD5Utils;
import com.example.demo2.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @Value("${mail.fromMail.sender}")
    private String sender;// 发送者

    @Autowired
    private JavaMailSender javaMailSender;

    private Map<String, Object> resultMap=new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String RegisterHtml() {
        return "index";
    }

    //跳转到注册页面
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //注册处理
    @PostMapping("/register")
    public String register(String username, String password, String password2, String email, String checkcode, RedirectAttributes attributes) {
        if(resultMap==null){ return "/register"; }
        //判断验证码是否正确
        String requestHash = resultMap.get("hash").toString();
        String tamp = resultMap.get("tamp").toString();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");//当前时间
        Calendar c = Calendar.getInstance();
        String currentTime = sf.format(c.getTime());
        if (tamp.compareTo(currentTime) > 0) {
            String hash =  MD5Utils.code(checkcode);//生成MD5值
            if (hash.equalsIgnoreCase(requestHash)){
                //校验成功
                if (password.equals(password2)) {
                    User u = new User(username, password,email);
                    int result = userService.register(u);
                    //成功 跳转到登录页面
                    if (result==1) {
                        attributes.addFlashAttribute("msg", "恭喜！现在，你可以登录你的用户名。");
                        return "redirect:/login";
                    }
                    //失败 提示信息
                    else if(result==2){
                        attributes.addFlashAttribute("msg", "该邮箱已注册");
                        System.out.println("\n该邮箱已注册\n");
                    }
                    else{
                        attributes.addFlashAttribute("msg", "用户名已存在");
                        System.out.println("\n用户名已存在\n");
                    }
                }
                //密码和确认密码不一致  在注册页面上显示提示信息
                else attributes.addFlashAttribute("msg", "密码不一致");
            }else {
                //验证码不正确，校验失败
                System.out.println("2");
                attributes.addFlashAttribute("msg", "验证码输入不正确");
            }
        } else {
            // 超时
            System.out.println("3");
            attributes.addFlashAttribute("msg", "验证码已过期");
        }
        return "redirect:/register";
    }

    @RequestMapping("/sendEmail")
    //转换json数据
    @ResponseBody
    public String sendEmail(String email) {
        System.out.println("\n发送邮件\n");
        SimpleMailMessage message = new SimpleMailMessage();
        String code = VerifyCode(6);    //随机数生成6位验证码
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("航空订票系统");// 标题
        message.setText("【航空订票系统】你的验证码为："+code+"，有效时间为5分钟(若不是本人操作，可忽略该条邮件)");// 内容
        try {
            javaMailSender.send(message);
            logger.info("文本邮件发送成功！");
            saveCode(code);
            return "success";
        }catch (MailSendException e){
            logger.error("目标邮箱不存在");
            return "false";
        } catch (Exception e) {
            logger.error("文本邮件发送异常！", e);
            return "failure";
        }
    }

    //生成随机验证码
    private String VerifyCode(int n){
        Random r = new Random();
        StringBuffer sb =new StringBuffer();
        for(int i = 0;i < n;i ++){
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
        return sb.toString();
    }

    //保存验证码和时间
    private void saveCode(String code){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期

        String hash =  MD5Utils.code(code);//生成MD5值
        resultMap.put("hash", hash);
        resultMap.put("tamp", currentTime);
    }


    //跳转到登录页面
    @GetMapping(value={"/login"})
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/success")
    public String  success(){
        return "index";
    }

    @RequestMapping("/fail")
    public String  fail(){
        return "fail";
    }

    //重置密码
    @RequestMapping("/reset")
    public String  reset(){
        return "reset";
    }

    //处理
    @PostMapping("/reset")
    public String reset(String password, String password2, String email, String checkcode, RedirectAttributes attributes){
        User u = userService.selectUserByEmail(email);
        //没有用户使用该邮箱
        if(u==null){
            attributes.addFlashAttribute("msg", "请输入正确的邮箱地址！");
            return "redirect:/reset";
        }
        else {
            //判断验证码是否正确
            String requestHash = resultMap.get("hash").toString();
            String tamp = resultMap.get("tamp").toString();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");//当前时间
            Calendar c = Calendar.getInstance();
            String currentTime = sf.format(c.getTime());
            if (tamp.compareTo(currentTime) > 0) {
                String hash = MD5Utils.code(checkcode);//生成MD5值
                if (hash.equalsIgnoreCase(requestHash)) {
                    //校验成功
                    if (password.equals(password2)) {
                        //成功 跳转到登录页面
                        userService.resetPwd(u);
                        attributes.addFlashAttribute("msg", "重置密码成功");
                        return "redirect:/login";
                    }
                    //密码和确认密码不一致  在注册页面上显示提示信息
                    else attributes.addFlashAttribute("msg", "密码不一致");
                    //注册不成功就保持在注册页面

                } else {
                    //验证码不正确，校验失败
                    System.out.println("2");
                    attributes.addFlashAttribute("msg", "验证码输入不正确");
                }
            } else {
                // 超时
                System.out.println("3");
                attributes.addFlashAttribute("msg", "验证码已过期");
            }
        }
        return "redirect:/reset";
    }

}
