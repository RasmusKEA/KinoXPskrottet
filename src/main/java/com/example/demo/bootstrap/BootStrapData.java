package com.example.demo.bootstrap;

import com.example.demo.model.Movie;
import com.example.demo.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final MovieRepository movieRepository;

    public BootStrapData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie();
        movie.setMovieTitle("Rejsen til Saturn");
        movie.setGenre("Komedie");
        movie.setReleaseYear(2006);


        Movie movie1 = new Movie();
        movie1.setMovieTitle("King kong vs Godzilla");
        movie1.setGenre("Dokumentar");
        movie1.setReleaseYear(2006);


        Movie movie2 = new Movie();
        movie2.setMovieTitle("Terkel i Knibe");
        movie2.setGenre("Komedie");
        movie2.setReleaseYear(2006);

        movieRepository.save(movie);
        movieRepository.save(movie1);
        movieRepository.save(movie2);

    }
}
