package com.tutorial.elasticsearch.movie.service;

import com.tutorial.elasticsearch.movie.data.Movie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieProvider {
    /**
     * @return all available {@link Movie} objects
     */
    List<Movie> getAllMovies();

    /**
     * @return the total number of {@link Movie} objects
     */
    long countMovies();

    /**
     * return the list of {@link Movie} objects for input {@link Pageable} request
     *
     * @param pageable
     * @return
     */
    List<Movie> getAllMovies(Pageable pageable);

    /**
     * removes the {@link Movie} with input id
     * @param id
     */
    void removeMovie(String id);
}
