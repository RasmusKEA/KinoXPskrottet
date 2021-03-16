package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String Login() {
        return "login";
    }


    @GetMapping("/profile")
    public String Profile() {
        return "profile";
    }

    @GetMapping("/register")
    public String Register() {
        return "register";
    }
}
