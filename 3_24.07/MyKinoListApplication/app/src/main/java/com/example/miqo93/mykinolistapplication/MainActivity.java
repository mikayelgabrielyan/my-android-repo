package com.example.miqo93.mykinolistapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ObjectFilm> movieList = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.film_recycler_list);
        FilmAdapter filmAdapter = new FilmAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(filmAdapter);
        creatMovies();
    }

    private void creatMovies() {
        ObjectFilm movie;
        for(int i = 1; i < 11; ++i) {
            String str = "Film" + i;
            movie = new ObjectFilm(str, getResources().getDrawable(R.drawable.movie), "Its Description", 5, false);
            movieList.add(movie);
        }
    }
}
