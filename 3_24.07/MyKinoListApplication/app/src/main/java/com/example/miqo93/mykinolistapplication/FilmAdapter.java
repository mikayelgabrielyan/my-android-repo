package com.example.miqo93.mykinolistapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private List<ObjectFilm> mMoviesList;

    FilmAdapter(List<ObjectFilm> moviesList) {
        this.mMoviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        public ImageView image;
        TextView tvDescription;
        RatingBar ratingBar;

        MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.movie_title);
            image = view.findViewById(R.id.movie_imgage);
            ratingBar = view.findViewById(R.id.movie_rating);
            tvDescription = view.findViewById(R.id.movie_description);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_movie, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ObjectFilm movie = mMoviesList.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.image.setImageDrawable(movie.getImage());
        holder.tvDescription.setText(movie.getDescription());
        holder.ratingBar.setNumStars(movie.getRating());
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }
}