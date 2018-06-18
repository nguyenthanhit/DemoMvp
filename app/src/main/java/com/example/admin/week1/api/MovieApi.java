package com.example.admin.week1.api;

import com.example.admin.week1.model.NowPlaying;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 *  Created by JAY on 18/06/2018
 */

public interface MovieApi {

    @GET("now_playing")
    Call<NowPlaying> nowPlaying();


}
