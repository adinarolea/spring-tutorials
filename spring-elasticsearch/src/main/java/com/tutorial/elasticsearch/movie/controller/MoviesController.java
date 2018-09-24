package com.tutorial.elasticsearch.movie.controller;

import com.tutorial.elasticsearch.movie.service.MovieProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class MoviesController {

    @Autowired
    MovieProvider movieProvider;

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping(value = "/movies", produces = "application/json")
    @ResponseBody
    public MovieResponse getMovies(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize) {
        return new MovieResponse(movieProvider.getAllMovies(PageRequest.of(pageNumber - 1, pageSize)).stream().map(MovieData::new).collect(Collectors.toList()), movieProvider.countMovies());
    }

    @DeleteMapping(value = "/movie/{id}", produces = "application/json")
    @ResponseBody
    public void removeMovie(@PathVariable(value = "id") String id) {
        movieProvider.removeMovie(id);
    }
}
