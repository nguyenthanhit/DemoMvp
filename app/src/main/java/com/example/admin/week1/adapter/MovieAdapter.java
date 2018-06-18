package com.example.admin.week1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.week1.R;
import com.example.admin.week1.model.Movie;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Created by JAY on 18/06/2018
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int POPULAR = 1;
    private static final int NORMAL = 0;
    private final List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context) {
        movies = new ArrayList<>();
        this.context = context;
    }

    public void setData(List<Movie> data) {
        movies.clear();
        movies.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = movies.get(position);
        if (movie.getVoteAverage() > 5) {
            return POPULAR;
        }
        return NORMAL;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == POPULAR) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_movie_popular, parent, false);
            return new PopularViewHolder(view);
        } else {
            return new NormalViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.item_movie_normal, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (holder instanceof PopularViewHolder) {
            bindPopularViewHolder((PopularViewHolder) holder, movie);
        } else if (holder instanceof NormalViewHolder) {
            bindNormalViewHolder((NormalViewHolder) holder, movie);
        }
    }

    private void bindNormalViewHolder(NormalViewHolder holder, Movie movie) {
        holder.tvTitle.setText(movie.getTitle());
        holder.tvDes.setText(movie.getOverview());
        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.ic_android))
                .load(movie.getBackdropPath())
                .into(holder.ivCover);
    }

    private void bindPopularViewHolder(PopularViewHolder holder, Movie movie) {
        Glide.with(context)
                .applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_android))
                .load(movie.getBackdropPath())
                .into(holder.ivCover);
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCover)

        ImageView ivCover;

        PopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCoverN)
        ImageView ivCover;

        @BindView(R.id.tvDes)
        TextView tvDes;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
