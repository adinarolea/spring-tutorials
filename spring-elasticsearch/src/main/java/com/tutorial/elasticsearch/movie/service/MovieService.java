package com.tutorial.elasticsearch.movie.service;

import com.tutorial.elasticsearch.movie.Movie;
import com.tutorial.elasticsearch.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        Page<Movie> moviePage = movieRepository.findAll();
        if (moviePage == null) {
            return Collections.emptyList();
        }
        return moviePage.getContent();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void addMovies() {
        movieRepository.save(new Movie());
    }
}
