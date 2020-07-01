package com.example.btlandroid.view;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.model.dataAPI.ObjectResponseMovie;
import com.example.btlandroid.utils.GetDataService;
import com.example.btlandroid.model.dataAPI.MovieData;
import com.example.btlandroid.utils.LoadingProgress;
import com.example.btlandroid.utils.NetworkReceiver;
import com.example.btlandroid.utils.RetrofitClientInstance;
import com.example.btlandroid.view.recycleview.ListMovieAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    NetworkReceiver networkReceiver = new NetworkReceiver();
    ImageButton btnSearch;
    TextView label;
    EditText inputSearch;
    RecyclerView viewListMovie;
    ArrayList<Movie> listMovies = new ArrayList<Movie>();

    String [] listSortType = new String[] {"Most Popular", "Highest Rates", "Now Playing", "Upcoming"};
    ImageButton btFilter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btFilter = findViewById(R.id.btnFilter);
        btFilter.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
            mBuilder.setTitle("Sort By");
            mBuilder.setSingleChoiceItems(listSortType, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    getListMovie(getMovieType(i),1);
                    dialog.dismiss();
                }
            });
            mBuilder.setPositiveButton("Cancel", (dialog, which) -> {
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        });

        initRecycleView();
        label = findViewById(R.id.label);
        inputSearch = findViewById(R.id.inputmovie);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(v -> {
            if (label.getVisibility() == View.INVISIBLE) {
                label.setVisibility(View.VISIBLE);
                inputSearch.setVisibility(View.INVISIBLE);
            } else {
                label.setVisibility(View.INVISIBLE);
                inputSearch.setVisibility(View.VISIBLE);
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
        getListMovie(getMovieType(0),1);
    }

    public String getMovieType(int type) {
        switch (type) {
            case 0:
                return "popular";
            case 1:
                return "top_rated";
            case 2:
                return "now_playing";
            case 3:
                return "upcoming";
            default:
                return null;
        }
    }

    // getType = 1 (load), getType = 2 (load more)
    public void getListMovie(String type, int getType) {
        switch (getType) {
            case 1: {
                LoadingProgress.show(Home.this);
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<ObjectResponseMovie> call = service.getMovieList(
                        type,
                        Constant.API_KEY,
                        Constant.en,
                        1
                );
                call.enqueue(new Callback<ObjectResponseMovie>() {
                    @Override
                    public void onResponse(Call<ObjectResponseMovie> call, Response<ObjectResponseMovie> response) {
                        ArrayList<MovieData> lisResults = response.body().getResults();
                        listMovies.clear();
                        for (MovieData movie : lisResults) {
                            listMovies.add(new Movie(
                                    movie.getTitle(), movie.getOverview(), movie.getId(), movie.getVote_average(), movie.getPoster_path(),
                                    movie.getBackdrop_path(), movie.getRelease_date(),null,null,null,null
                            ));
                        }
                        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(listMovies, Home.this);
                        viewListMovie.setAdapter(listMovieAdapter);
                        LoadingProgress.hide();
                    }

                    @Override
                    public void onFailure(Call<ObjectResponseMovie> call, Throwable t) {

                    }
                });
                break;
            }
            case 2: {
                break;
            }
            default:
                break;
        }
    }

}
