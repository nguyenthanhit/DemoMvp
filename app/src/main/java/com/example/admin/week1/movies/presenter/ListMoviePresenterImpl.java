package com.example.admin.week1.movies.presenter;

import com.example.admin.week1.model.Movie;
import com.example.admin.week1.movies.repository.DataListener;
import com.example.admin.week1.movies.repository.MovieRepository;
import com.example.admin.week1.movies.view.ListMovieView;
import com.example.admin.week1.util.NetWorkUtil;

import java.util.List;

/**
 *  Created by JAY on 18/06/2018
 */

public class ListMoviePresenterImpl implements ListMoviePresenter, DataListener {
    private ListMovieView mView;
    private MovieRepository movieRepository;

    public ListMoviePresenterImpl(ListMovieView mView, MovieRepository movieRepository) {
        this.mView = mView;
        this.movieRepository = movieRepository;
    }

    @Override
    public void getMovies() {
        mView.showLoading();
        if (NetWorkUtil.isOnline())
            movieRepository.getDataFromNetWork(this);
        else
            movieRepository.getDataFromLocal(this);
    }

    @Override
    public void onResponse(List<Movie> movies) {
        String test = "thanh/nguyen/";
        mView.hideLoading();
        mView.showListMovies(movies);
    }

    @Override
    public void onError(String error) {
        mView.hideLoading();
        mView.showError();
    }
}
