package com.example.admin.week1.movies.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.week1.R;
import com.example.admin.week1.adapter.MovieAdapter;
import com.example.admin.week1.model.Movie;
import com.example.admin.week1.movies.presenter.ListMoviePresenter;
import com.example.admin.week1.movies.presenter.ListMoviePresenterImpl;
import com.example.admin.week1.movies.repository.MovieRepository;
import com.example.admin.week1.movies.repository.MovieRepositoryImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListMovieActivity extends AppCompatActivity implements ListMovieView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rvListMovies)
    RecyclerView rvMovies;

    MovieAdapter movieAdapter;

    ListMoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupView();
        // create repository
        MovieRepository repository = new MovieRepositoryImpl();
        presenter = new ListMoviePresenterImpl(this, repository);

    }

    void setupView() {
        movieAdapter = new MovieAdapter(this);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(movieAdapter);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btnFetchData)
    public void fetchData() {
        presenter.getMovies();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "May be have error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showListMovies(List<Movie> movies) {
        movieAdapter.setData(movies);
    }
}
