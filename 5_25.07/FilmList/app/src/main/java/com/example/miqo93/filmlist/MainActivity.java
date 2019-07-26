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
        movieList.add(new Film("The Godfather", "This article is about the 1972 film. For the original novel on which the film is based, see The Godfather (novel). For male godparent in many Christian traditions", "https://en.wikipedia.org/wiki/The_Godfather", R.drawable.godfather));
        movieList.add(new Film("John Wick", "In June 2017, it was announced Chad Stahelski and writer Derek Kolstad were both creating a television series for Lionsgate based on the characters and setting of the John Wick films, tentatively titled - The Continental.", "https://en.wikipedia.org/wiki/John_Wick", R.drawable.johnwick));
        movieList.add(new Film("X-Man", "X-Men is an American superhero film series based on the fictional superhero team of the same name, who originally appeared in a series of comic books created by Stan Lee and Jack Kirby and published by Marvel Comics", "https://en.wikipedia.org/wiki/X-Men_(film_series)", R.drawable.xman));
        movieList.add(new Film("Now You See Me", "Now You See Me is a 2013 American heist thriller film directed by Louis Leterrier and written by Ed Solomon, Boaz Yakin, and Edward Ricourt.", "https://en.wikipedia.org/wiki/Now_You_See_Me_(film)", R.drawable.iluzia));
        movieList.add(new Film("Thor", "In Germanic religion, Thor is a hammer-wielding god associated with thunder, lightning, storms, the protection of mankind, oak trees, strength, and also hallowing and fertility", "https://en.wikipedia.org/wiki/Thor", R.drawable.thor));
        movieList.add(new Film("Iron Man", "Iron Man is a fictional superhero appearing in American comic books published by Marvel Comics", "https://en.wikipedia.org/wiki/Iron_Man", R.drawable.ironman));
        movieList.add(new Film("Transformers","Transformers is a 2007 American science fiction action film based on the toy line of the same name created by Hasbro" , "https://en.wikipedia.org/wiki/Transformers_(film)", R.drawable.transformers));
        movieList.add(new Film("The Transporter", "The Transporter is a 2002 English-language French action film directed by Corey Yuen and Louis Leterrier and written by Luc Besson, who was inspired by BMW Films' The Hire series", "https://en.wikipedia.org/wiki/The_Transporter", R.drawable.perevozchik));
    }
}
