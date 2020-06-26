package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Trailer;
import com.example.btlandroid.view.recycleview.ListTrailerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity {

    RecyclerView viewListTrailers;
    ImageView back;
    ImageView backdrop;
    TextView movieName, releaseDate, rate, genres, summary;
    ArrayList<Trailer> listTrailers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        init();

        back = findViewById(R.id.iconBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent(MovieDetail.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void init() {
        backdrop = findViewById(R.id.backdrop);
        Picasso.get().load(Constant.BASE_URL_IMAGE + "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg").into(backdrop);
        movieName = findViewById(R.id.titleName);
        movieName.setText("Sonic the Hedgehog");
        releaseDate = findViewById(R.id.releaseDate);
        releaseDate.setText("Release Date: 2020-02-12");
        rate = findViewById(R.id.rate);
        rate.setText("Rate: 7.5/10");
        genres = findViewById(R.id.genres);
        genres.setText("Genres: Action, Comedy, Science Fiction, Family");
        summary = findViewById(R.id.summary);
        summary.setText("Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.");
        viewListTrailers = findViewById(R.id.listTrailers);
        viewListTrailers.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieDetail.this, RecyclerView.HORIZONTAL,false);
        viewListTrailers.setLayoutManager(layoutManager);
        listTrailers.add(new Trailer(
            "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
            null
        ));
        listTrailers.add(new Trailer(
                "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                null
        ));
        listTrailers.add(new Trailer(
                "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                null
        ));
        ListTrailerAdapter trailerAdapter = new ListTrailerAdapter(listTrailers, MovieDetail.this);
        viewListTrailers.setAdapter(trailerAdapter);
    }
}
