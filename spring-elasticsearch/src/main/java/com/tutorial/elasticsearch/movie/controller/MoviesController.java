package com.tutorial.elasticsearch.movie.controller;

import com.tutorial.elasticsearch.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@Controller
public class MoviesController {

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping(value = "/movies", produces = "application/json")
    @ResponseBody
    public MovieResponse getMovies(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize) {
        return new MovieResponse(movieService.getAllMovies(new PageRequest(pageNumber, pageSize)).stream().map(MovieData::new).collect(Collectors.toList()));

    }

}
