package com.example.btlandroid.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.btlandroid.R;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.view.recycleview.ListMovieAdapter;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ImageButton btnSearch;
    TextView label;
    EditText inputSearch;
    RecyclerView viewListMovie;
    ArrayList<Movie> listMovies = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initRecycleView();

        label = findViewById(R.id.label);
        inputSearch = findViewById(R.id.inputmovie);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (label.getVisibility() == View.INVISIBLE) {
                    label.setVisibility(View.VISIBLE);
                    inputSearch.setVisibility(View.INVISIBLE);
                } else {
                    label.setVisibility(View.INVISIBLE);
                    inputSearch.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void initRecycleView() {
        viewListMovie = findViewById(R.id.listmovie);
        viewListMovie.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Home.this,2);
        viewListMovie.setLayoutManager(layoutManager);
        listMovies.add(new Movie(
            "Britt-Marie Was Here", null, 0, 0, "/1Duc3EBiegywczxTWekvy03HWai.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Wasp Network", null, 0, 0, "/fOvqEunubL3wPskvtk2Ficfl0pH.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Britt-Marie Was Here", null, 0, 0, "/1Duc3EBiegywczxTWekvy03HWai.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Wasp Network", null, 0, 0, "/fOvqEunubL3wPskvtk2Ficfl0pH.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Britt-Marie Was Here", null, 0, 0, "/1Duc3EBiegywczxTWekvy03HWai.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Wasp Network", null, 0, 0, "/fOvqEunubL3wPskvtk2Ficfl0pH.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Britt-Marie Was Here", null, 0, 0, "/1Duc3EBiegywczxTWekvy03HWai.jpg", null, null, null, null, null, null
        ));
        listMovies.add(new Movie(
                "Wasp Network", null, 0, 0, "/fOvqEunubL3wPskvtk2Ficfl0pH.jpg", null, null, null, null, null, null
        ));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(listMovies, Home.this);
        viewListMovie.setAdapter(listMovieAdapter);
    }

}
