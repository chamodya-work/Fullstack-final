package com.movie.fullstack.controller;

import com.movie.fullstack.model.Movie;
import com.movie.fullstack.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/getMovieById/{movie_id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movie_id") String movie_id){
        return new ResponseEntity<Movie>(movieService.getMovieById(movie_id), HttpStatus.CREATED);
    }
}
