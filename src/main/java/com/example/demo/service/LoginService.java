package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.ui.ModelMap;

public class LoginService {

    private UserRepository jdbc;

    public LoginService() {
        jdbc = new UserRepository();
        jdbc.setConnection();
    }

    public int getUserID(String username, String password){
        return jdbc.getUserFromLogin(username,password);
    }

}