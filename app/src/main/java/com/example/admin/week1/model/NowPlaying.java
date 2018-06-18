package com.example.admin.week1.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *  Created by JAY on 18/06/2018
 */

public class NowPlaying {
    @SerializedName("results")
    private  List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
