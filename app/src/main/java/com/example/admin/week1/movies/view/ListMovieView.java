package com.example.admin.week1.movies.view;

import com.example.admin.week1.model.Movie;

import java.util.List;

/**
 *  Created by JAY on 18/06/2018
 */

public interface ListMovieView {
    void showLoading();

    void hideLoading();

    void showListMovies(List<Movie> movies);

    void showError();
}
