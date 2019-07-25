package com.tutorial.elasticsearch.movie.service;

import com.tutorial.elasticsearch.movie.data.Movie;
import com.tutorial.elasticsearch.movie.repository.MovieRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class MovieService implements MovieProvider {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        Page<Movie> moviePage = movieRepository.findAll();
        if (moviePage == null) {
            return Collections.emptyList();
        }
        return moviePage.getContent();
    }

    @Override
    public long countMovies() {
        return movieRepository.count();
    }

    @Override
    public List<Movie> getAllMovies(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        if (moviePage == null) {
            return Collections.emptyList();
        }
        return moviePage.getContent();
    }

    @Override
    public void removeMovie(String id) {
        movieRepository.deleteById(id);
    }

    /**
     * fills the repository with mock data on startup
     */
    @EventListener(ContextRefreshedEvent.class)
    public void addMovies() {
        if (!movieRepository.findAll().getContent().isEmpty()) {
            return;
        }
        for (int i = 0; i < 50; i++) {
            Movie movie = new Movie();
            movie.setDirector(RandomStringUtils.randomAlphanumeric(5));
            movie.setId(RandomStringUtils.randomAlphanumeric(5));
            movie.setTitle(RandomStringUtils.randomAlphabetic(7));
            movie.setRating(BigDecimal.valueOf(1 + Math.random() * (10 - 1)).setScale(1, BigDecimal.ROUND_HALF_UP));
            movieRepository.save(movie);
        }
    }
}
