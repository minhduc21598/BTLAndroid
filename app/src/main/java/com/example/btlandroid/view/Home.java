package com.example.btlandroid.view;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlandroid.R;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.utils.NetworkReceiver;
import com.example.btlandroid.view.recycleview.ListMovieAdapter;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    NetworkReceiver networkReceiver = new NetworkReceiver();
    ImageButton btnSearch;
    TextView label;
    EditText inputSearch;
    RecyclerView viewListMovie;
    ArrayList<Movie> listMovies = new ArrayList<Movie>();

    String [] listSortType;
    ImageButton btFilter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btFilter = (ImageButton) findViewById(R.id.btnFilter);
        btFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSortType = new String[] {"Most Popular", "Highest Rates", "Favorites", "Upcoming"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
                mBuilder.setTitle("Sort By");
                mBuilder.setSingleChoiceItems(listSortType, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //Event click Sort type
                        Toast.makeText(Home.this, "Sort by: " + listSortType[i], Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                mBuilder.setPositiveButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                //show alert dialog
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

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

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkReceiver);
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
