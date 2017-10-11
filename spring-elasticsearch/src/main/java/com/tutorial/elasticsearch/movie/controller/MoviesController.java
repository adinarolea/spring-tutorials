package com.tutorial.elasticsearch.movie.controller;

import com.tutorial.elasticsearch.movie.service.MovieProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return new MovieResponse(movieProvider.getAllMovies(new PageRequest(pageNumber - 1, pageSize)).stream().map(MovieData::new).collect(Collectors.toList()), movieProvider.countMovies());
    }

    @PostMapping(value = "/remove/movie", produces = "application/json")
    @ResponseBody
    public void removeMovie(@RequestParam(value = "id") String id) {
        movieProvider.removeMovie(id);
    }
}
