package com.example.demo.service;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.ui.ModelMap;

import java.sql.Date;

public class RegisterService {

    private UserRepository jdbc;

    public RegisterService() {
        jdbc = new UserRepository();
        jdbc.setConnection();
    }

    public int createProfile(String firstName, String surname, Date birthday, String username, String email, String password) {
        return jdbc.createProfile(firstName, surname, birthday, username, email, password);
    }

    public void getUserInfo(int id, ModelMap modelMap){
        UserModel userModel = jdbc.getUserInfo(id);

        if (userModel == null){
            modelMap.addAttribute("errorMessage", "Kunne ikke finde din profil");
        }
        else {
            modelMap.addAttribute("UserModel", userModel);
        }
    }

    public boolean checkRegistration(String username, String email, String password){
        boolean b = true;
        if (username == null || username.length() > 150){
            b = false;
        }
        else if(email == null || email.length() > 256){
            b = false;
        }
        else if(password == null || password.length() > 150){
            b = false;
        }
        return b;
    }

    public void deleteProfile(int id, ModelMap modelMap){
        UserRepository deleteProfile = jdbc.deleteProfile(id);

        if (deleteProfile == null){
            modelMap.addAttribute("errorMessage", "Kunne ikke slette din profil");
        }
        else{
            modelMap.addAttribute("deleteProfile", deleteProfile);
        }
    }
}
