package com.example.btlandroid.view;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Genres;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.model.dataAPI.genres.ObjectResponseGenres;
import com.example.btlandroid.model.dataAPI.movie.ObjectResponseMovie;
import com.example.btlandroid.utils.GetDataService;
import com.example.btlandroid.model.dataAPI.movie.MovieData;
import com.example.btlandroid.utils.GetListGenres;
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
    SwipeRefreshLayout refreshLayout;

    ArrayList<Movie> listMovies = new ArrayList<Movie>();
    int page = 1;
    int selectedType = 0;

    ImageButton btFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponent();

        getListGenres();
        initRecycleView();

        refreshing();

    }

    public void initComponent() {
        btFilter = findViewById(R.id.btnFilter);
        btFilter.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Home.this);
            mBuilder.setTitle("Sort By");
            mBuilder.setSingleChoiceItems(Constant.LIST_SORT_TYPE, selectedType, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    selectedType = i;
                    getListMovie(getMovieType(selectedType),false,false);
                    dialog.dismiss();
                }
            });
            mBuilder.setPositiveButton("Cancel", (dialog, which) -> {
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
        });

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
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LoadingProgress.show(Home.this);
                searchMovie(inputSearch.getText().toString().trim());
                LoadingProgress.hide();
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        getListMovie(getMovieType(selectedType),false,false);
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

    public void getListMovie(String type, boolean refreshing, boolean loadMore) {
        if (!refreshing) {
            LoadingProgress.show(Home.this);
        }
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseMovie> call = service.getMovieList(
                type,
                Constant.API_KEY,
                Constant.en,
                page
        );
        call.enqueue(new Callback<ObjectResponseMovie>() {
            @Override
            public void onResponse(Call<ObjectResponseMovie> call, Response<ObjectResponseMovie> response) {
                ArrayList<MovieData> lisResults = response.body().getResults();
                if (!loadMore) listMovies.clear();
                for (MovieData movie : lisResults) {
                    ArrayList<Genres> list = new ArrayList<>();
                    for(int id : movie.getGenre_ids()) {
                        for (int i = 0;i < GetListGenres.getListGenres().size();i++) {
                            if (id == GetListGenres.getListGenres().get(i).getId()) list.add(GetListGenres.getListGenres().get(i));
                        }
                    }
                    listMovies.add(new Movie(
                            movie.getTitle(), movie.getOverview(), movie.getId(), movie.getVote_average(), movie.getPoster_path(),
                            movie.getBackdrop_path(), movie.getRelease_date(),null, list,null,null
                    ));
                }
                ListMovieAdapter listMovieAdapter = new ListMovieAdapter(listMovies, Home.this);
                viewListMovie.setAdapter(listMovieAdapter);
                if (!refreshing) {
                    LoadingProgress.hide();
                }
            }

            @Override
            public void onFailure(Call<ObjectResponseMovie> call, Throwable t) {

            }
        });
    }

    public void getListGenres() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseGenres> call = service.getAllGenres(
                Constant.API_KEY
        );
        call.enqueue(new Callback<ObjectResponseGenres>() {
            @Override
            public void onResponse(Call<ObjectResponseGenres> call, Response<ObjectResponseGenres> response) {
                GetListGenres.setListGenres(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<ObjectResponseGenres> call, Throwable t) {

            }
        });
    }

    public void refreshing() {
        refreshLayout = findViewById(R.id.pullToRefresh);
        refreshLayout.setOnRefreshListener(() -> {
            getListMovie(getMovieType(selectedType),true,false);
            refreshLayout.setRefreshing(false);
        });
    }

    public void searchMovie(String keyWord) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseMovie> call = service.getSearch(
                Constant.API_KEY,
                Constant.en,
                keyWord,
                page
        );
        call.enqueue(new Callback<ObjectResponseMovie>() {
            @Override
            public void onResponse(Call<ObjectResponseMovie> call, Response<ObjectResponseMovie> response) {
                ArrayList<MovieData> lisResults = response.body().getResults();
                listMovies.clear();
                for (MovieData movie : lisResults) {
                    ArrayList<Genres> list = new ArrayList<>();
                    for(int id : movie.getGenre_ids()) {
                        for (int i = 0;i < GetListGenres.getListGenres().size();i++) {
                            if (id == GetListGenres.getListGenres().get(i).getId()) list.add(GetListGenres.getListGenres().get(i));
                        }
                    }
                    listMovies.add(new Movie(
                            movie.getTitle(), movie.getOverview(), movie.getId(), movie.getVote_average(), movie.getPoster_path(),
                            movie.getBackdrop_path(), movie.getRelease_date(),null, list,null,null
                    ));
                }
                ListMovieAdapter listMovieAdapter = new ListMovieAdapter(listMovies, Home.this);
                viewListMovie.setAdapter(listMovieAdapter);
            }

            @Override
            public void onFailure(Call<ObjectResponseMovie> call, Throwable t) {

            }
        });
    }

}
