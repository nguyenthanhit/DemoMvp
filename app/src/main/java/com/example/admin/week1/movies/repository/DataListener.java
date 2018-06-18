package com.example.admin.week1.movies.repository;
/* *
 *  Created by JAY on 18/06/2018
 */

import com.example.admin.week1.model.Movie;

import java.util.List;

public interface DataListener {
    void onResponse(List<Movie> movies);

    void onError(String error);
}
