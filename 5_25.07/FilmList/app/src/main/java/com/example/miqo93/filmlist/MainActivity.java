package com.example.miqo93.filmlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Film> movieList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        AdapterFilm filmAdapter = new AdapterFilm(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(filmAdapter);
        creatMovies();
    }

    private void creatMovies() {
        Film movie;

        movie = new Film("X-Man11", "ghkgfgkfgkagg gfgafgagf agfjkagfkjag", "https://www.ivi.ru/watch/171073/description", R.drawable.movie);
        movieList.add(movie);
        movie = new Film("X-Man22", "ghkgfgkfgkagg gfgafgagf agfjkagfkjag", "https://www.ivi.ru/watch/171073/description", R.drawable.movie);
        movieList.add(movie);
        movie = new Film("X-Man33", "ghkgfgkfgkagg gfgafgagf agfjkagfkjag", "https://www.ivi.ru/watch/171073/description", R.drawable.movie);
        movieList.add(movie);
        movie = new Film("X-Man44", "ghkgfgkfgkagg gfgafgagf agfjkagfkjag", "https://www.ivi.ru/watch/171073/description", R.drawable.movie);
        movieList.add(movie);
        movie = new Film("X-Man55", "ghkgfgkfgkagg gfgafgagf agfjkagfkjag", "https://www.ivi.ru/watch/171073/description", R.drawable.movie);
        movieList.add(movie);
    }


}
