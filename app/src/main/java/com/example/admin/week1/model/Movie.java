package com.example.admin.week1.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *  Created by JAY on 18/06/2018
 */


public class Movie implements Serializable {
    private static final String URL_IMAGE = "https://image.tmdb.org/t/p/w500/";

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private double voteAverage;

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return URL_IMAGE + backdropPath;
    }

}
