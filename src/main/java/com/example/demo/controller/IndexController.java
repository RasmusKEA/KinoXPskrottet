package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

<<<<<<< HEAD
    @GetMapping("/movies")
    public String getMovies(){
        return "movies";
    }
=======
>>>>>>> 7ed2242b10fc8ba831713caf20b6e9d8c250690e

}
