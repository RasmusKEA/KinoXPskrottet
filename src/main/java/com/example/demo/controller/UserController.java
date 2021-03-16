package com.example.demo.controller;

import com.example.demo.model.UserIdentification;
import com.example.demo.service.RegisterService;
import com.example.demo.service.ValidateUserService;
import com.example.demo.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@Controller
public class UserController {
    ValidateUserService validateUserService;
    LoginService loginService;
    RegisterService registerService;

    public UserController(){
        validateUserService = new ValidateUserService();
        loginService = new LoginService();
        registerService = new RegisterService();
    }

    @GetMapping("/login")
    public String login(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap){
        UserIdentification userIden = validateUserService.checkUser(cookieID);
        if(userIden == null){
            return "login";
        }
        else if(userIden.getUserID() > 0){
            return "redirect:movies";
        }
        return "login";
    }


    //UserIdentifying
    @GetMapping("/logout")
    public String logOut(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap){
        validateUserService.removeUserIdentification(cookieID);
        return "redirect:login";
    }

    //UserIdentifying
    @PostMapping("/loginRequest")
    public String loginRequest(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap, WebRequest webRequest){
        String username = webRequest.getParameter("username");
        String password = webRequest.getParameter("password");
        UserIdentification userIden = null;
            int userID = loginService.getUserID(username,password);
            if(userID > 0){
                userIden = validateUserService.createUserIdentification(userID);
                Cookie cookie = new Cookie("cookieID",userIden.getCookieID());
                cookie.setMaxAge(2592000);
                response.addCookie(cookie);
                return "redirect:profile";

        }
        modelMap.addAttribute("errorMessage","Forkert Brugernavn eller Kodeord");
        return "redirect:login";
    }



    //UserIdentifying
    @GetMapping("/register")
    public String register(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap){
        UserIdentification userIden = validateUserService.checkUser(cookieID);
        if(userIden == null){
            return  "register";
        }
        else if(userIden.getUserID() > 0){
            return "redirect:register";
        }
        return "register";
    }

    //UserIdentifying
    @PostMapping("/registrationRequest")
    public String registrationRequest(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap, WebRequest webRequest){
        String firstName = webRequest.getParameter("firstname");
        String surname = webRequest.getParameter("surname");
        Date birthday = Date.valueOf(webRequest.getParameter("birthday"));
        String username = webRequest.getParameter("username");
        String email = webRequest.getParameter("email");
        String password = webRequest.getParameter("password");
        UserIdentification userIden = validateUserService.checkUser(cookieID);
        if(userIden == null || registerService.checkRegistration(username, email, password)){
            int userID = registerService.createProfile(firstName, surname, birthday, username, email, password);
            if(userID > 0){
                userIden = validateUserService.createUserIdentification(userID);
                Cookie cookie = new Cookie("cookieID", userIden.getCookieID());
                cookie.setMaxAge(2592000);
                response.addCookie(cookie);
                return "redirect:movies";
            }
            else{
                return "redirect:login";
            }
        }
        return "redirect:movies";
    }

    //Profile
    @GetMapping("/profile")
    public String profile(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap, WebRequest request){
        UserIdentification userIden = validateUserService.checkUser(cookieID);
        if(userIden == null){
            return "redirect:login";
        }
        else if(userIden.getUserID() < 0){
            return "redirect:login";
        }
        registerService.getUserInfo(userIden.getUserID(), modelMap);
        modelMap.addAttribute("userIden", userIden);
        return "profile";
    }

    //Profile
    @PostMapping("/deleteProfile")
    public String deleteProfile(@CookieValue(value = "cookieID", defaultValue = "") String cookieID, HttpServletResponse response, ModelMap modelMap, WebRequest request){
        UserIdentification userIden = validateUserService.checkUser(cookieID);
        if(userIden == null){
            return "redirect:login";
        }
        registerService.deleteProfile(userIden.getUserID(), modelMap);
        validateUserService.removeUserIdentification(userIden.getCookieID());
        return "redirect:login";
    }

}
