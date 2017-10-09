package com.tutorial.elasticsearch.movie.controller;

import com.tutorial.elasticsearch.movie.Movie;

import java.time.format.DateTimeFormatter;

public class MovieData {
    private String id;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public MovieData(Movie order) {
        this.id = order.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
