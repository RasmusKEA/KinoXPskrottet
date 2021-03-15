package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private MovieRepository movieRepository;
    //Klassen her er tiltænkt at være vores RestController. Altså indeholde de metoder der skal returnere et html svar og laves om til JSON


    public RestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/getAllMovies")
    public List<Movie> findAllMovies(){
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

}
