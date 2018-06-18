package com.example.admin.week1.movies.repository;
/* *
 *  Created by JAY on 18/06/2018
 */

import android.os.Looper;
import android.util.Log;
import com.example.admin.week1.api.MovieApi;
import com.example.admin.week1.helper.DbHelper;
import com.example.admin.week1.model.Movie;
import com.example.admin.week1.model.NowPlaying;
import com.example.admin.week1.util.RetrofitUtil;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryImpl implements MovieRepository {
    private MovieApi movieApi;

    public MovieRepositoryImpl() {
        movieApi = RetrofitUtil.createService();
    }

    @Override
    public void getDataFromNetWork(final DataListener listener) {
        movieApi.nowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                if (response.body() != null && response.body().getMovies() != null) {
                    List<Movie> movies = response.body().getMovies();
                    // save data to db
                    DbHelper.getInstance().insertMovies(movies);
                    listener.onResponse(movies);
                }
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getDataFromLocal(final DataListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Movie> movies = DbHelper.getInstance().getMovies();
                Log.d("Thanh", movies.size() + "");
                new android.os.Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (movies.size() > 0)
                            listener.onResponse(movies);
                        else
                            listener.onError("");
                        Log.d("Thanh", Thread.currentThread().getName() + "");
                    }
                });
            }
        }).start();
    }
}
