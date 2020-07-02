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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    ImageButton btnSearch, btnFilter;
    TextView label;
    EditText inputSearch;
    RecyclerView viewListMovie;
    SwipeRefreshLayout refreshLayout;

    ListMovieAdapter listMovieAdapter;

    ArrayList<Movie> listMovies = new ArrayList<Movie>();
    int page = 1;
    String typeAction = "filter";
    String keyWordSearch = "";
    int selectedType = 0;

    ProgressBar footerLoading;

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
        footerLoading = findViewById(R.id.footerLoading);
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
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
                typeAction = "search";
                LoadingProgress.show(Home.this);
                keyWordSearch = inputSearch.getText().toString().trim();
                searchMovie(keyWordSearch,false);
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
        GridLayoutManager layoutManager = new GridLayoutManager(Home.this,2);
        viewListMovie.setLayoutManager(layoutManager);
        listMovieAdapter = new ListMovieAdapter(listMovies,Home.this);
        viewListMovie.setAdapter(listMovieAdapter);
        viewListMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findLastVisibleItemPosition() == listMovies.size() - 1) {
                    footerLoading.setVisibility(View.VISIBLE);
                    page += 1;
                    if (typeAction == "filter") {
                        getListMovie(getMovieType(selectedType),false,true);
                    } else {
                        searchMovie(keyWordSearch,true);
                    }
                }
            }
        });
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
        if (!refreshing && !loadMore) {
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
                    listMovieAdapter.setList(listMovies);
                }
                if (!refreshing && !loadMore) {
                    LoadingProgress.hide();
                }
                if (loadMore) {
                    footerLoading.setVisibility(View.GONE);
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
        page = 1;
        refreshLayout = findViewById(R.id.pullToRefresh);
        refreshLayout.setOnRefreshListener(() -> {
            getListMovie(getMovieType(selectedType),true,false);
            refreshLayout.setRefreshing(false);
        });
    }

    public void searchMovie(String keyWord, boolean loadMore) {
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
                if(response.code() == 200) {
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
                    listMovieAdapter.setList(listMovies);
                    if (loadMore) {
                        footerLoading.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ObjectResponseMovie> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
