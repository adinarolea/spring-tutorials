package com.tutorial.elasticsearch.movie.controller;

import com.tutorial.elasticsearch.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MoviesController {

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/movies")
    @ResponseBody
    public List<MovieData> getMovies() {
        return movieService.getAllMovies().stream().map(order -> new MovieData(order)).collect(Collectors.toList());
    }

}
