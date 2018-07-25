package com.example.miqo93.filmlist;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class AdapterFilm extends RecyclerView.Adapter<AdapterFilm.MyViewHolder> {
    private List<Film> mFilmList;

    public AdapterFilm(List<Film> mFilmList) {
        this.mFilmList = mFilmList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Film film = mFilmList.get(position);
        holder.tvTitle.setText(film.getmTitle());
        holder.image.setImageResource(film.getmImage());
        holder.ratingBar.setRating(film.getmRating());
        holder.tvDescription.setText(film.getmDescription());
        holder.like.setImageResource(R.drawable.ic_favorite_black);

    }

    @Override
    public int getItemCount() {
        return mFilmList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView image;
        TextView tvDescription;
        RatingBar ratingBar;
        ImageButton like;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_tv);
            image = itemView.findViewById(R.id.image_view);
            tvDescription =itemView.findViewById(R.id.description_tv);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            like = itemView.findViewById(R.id.image_btn);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mFilmList.get(getAdapterPosition()).ismLike()) {
                        mFilmList.get(getAdapterPosition()).setmLike(false);
                        like.setImageResource(R.drawable.ic_favorite_black);
                    } else {
                        mFilmList.get(getAdapterPosition()).setmLike(true);
                        like.setImageResource(R.drawable.ic_favorite);
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(mFilmList.get(getAdapterPosition()).getmUrl()));
                    view.getContext().startActivity(browserIntent);
                }
            });
        }
    }
}
