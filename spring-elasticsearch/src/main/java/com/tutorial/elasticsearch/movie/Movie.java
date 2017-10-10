package com.tutorial.elasticsearch.movie;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import sun.nio.cs.ext.Big5;

import java.math.BigDecimal;

@Document(indexName = "movie-index")
public class Movie {

    @Id
    private String id;

    private String title;

    private String director;

    private BigDecimal rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
