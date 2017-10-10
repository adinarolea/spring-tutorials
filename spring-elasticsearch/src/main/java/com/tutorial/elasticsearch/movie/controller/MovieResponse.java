package com.tutorial.elasticsearch.movie.controller;

import java.util.List;

public class MovieResponse {

    private int total;

    private List<MovieData> rows;

    public MovieResponse(List<MovieData> rows) {
        this.total = rows.size();
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MovieData> getRows() {
        return rows;
    }

    public void setRows(List<MovieData> rows) {
        this.rows = rows;
    }
}
