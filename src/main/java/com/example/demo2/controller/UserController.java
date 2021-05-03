package com.example.demo2.controller;

import com.example.demo2.bean.User;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    public UserService userService;

    @RequestMapping(value = {"/"})
    public String RegisterHtml() {
        return "login3";
    }

    //跳转到注册页面
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //注册处理
    @PostMapping("/register")
    public String register(String username, String password, String password2, Model model) {
        if (password.equals(password2)) {
            User u = new User(username, password);
            boolean ifInsert = userService.register(u);
            //成功 跳转到登录页面
            if (ifInsert) return "redirect:/login";
            //失败 提示信息
            else model.addAttribute("msg", "用户已存在");
        }
        //密码和确认密码不一致  在注册页面上显示提示信息
        else model.addAttribute("msg", "密码不一致");
        //注册不成功就保持在注册页面
        return "register";
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
}
