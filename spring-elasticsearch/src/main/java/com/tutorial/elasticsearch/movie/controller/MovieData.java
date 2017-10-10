package com.tutorial.elasticsearch.movie.controller;

import com.tutorial.elasticsearch.movie.Movie;

import java.time.format.DateTimeFormatter;

public class MovieData {
    private String id;

    private String title;

    private String director;

    private String rating;


    public MovieData(Movie movie) {
        this.id = movie.getId();
        this.title= movie.getTitle();
        this.director = movie.getDirector();
        this.rating= String.valueOf(movie.getRating());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }
}
