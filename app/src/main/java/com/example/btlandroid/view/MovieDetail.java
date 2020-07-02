package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Genres;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.model.Trailer;
import com.example.btlandroid.model.User;
import com.example.btlandroid.model.UserReview;
import com.example.btlandroid.model.dataAPI.movie.MovieData;
import com.example.btlandroid.model.dataAPI.movie.ObjectResponseMovie;
import com.example.btlandroid.model.dataAPI.review.ObjectResponseReview;
import com.example.btlandroid.model.dataAPI.review.ReviewData;
import com.example.btlandroid.model.dataAPI.trailer.ObjectResponseTrailer;
import com.example.btlandroid.model.dataAPI.trailer.TrailerData;
import com.example.btlandroid.utils.GetDataService;
import com.example.btlandroid.utils.GetListGenres;
import com.example.btlandroid.utils.LoadingProgress;
import com.example.btlandroid.utils.NetworkReceiver;
import com.example.btlandroid.utils.RetrofitClientInstance;
import com.example.btlandroid.utils.SaveCurrentMovie;
import com.example.btlandroid.view.recycleview.ListReviewAdapter;
import com.example.btlandroid.view.recycleview.ListSimilarAdapter;
import com.example.btlandroid.view.recycleview.ListTrailerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {

    NetworkReceiver networkReceiver = new NetworkReceiver();
    ScrollView scrollView;
    LinearLayout headerBack;
    RecyclerView viewListTrailers, viewListSimilar, viewListReview;
    ImageView back;
    ImageView backdrop;
    TextView movieName, releaseDate, rate, genres, summary, headerTitle;

    ArrayList<Trailer> listTrailers = new ArrayList<>();
    ArrayList<Movie> listSimilar = new ArrayList<Movie>();
    ArrayList<UserReview> listReviews = new ArrayList<>();
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        LoadingProgress.show(MovieDetail.this);

        movie = SaveCurrentMovie.getCurrentMovie();
        init();
        getTrailers();
        getReviews();
        getSimilar();

        LoadingProgress.hide();

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

    public void goBack() {
        Intent intent = new Intent(MovieDetail.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void init() {
        back = findViewById(R.id.iconBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        headerBack = findViewById(R.id.headerBack);
        headerTitle = findViewById(R.id.detailTxt);
        scrollView = findViewById(R.id.scroll);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headerBack.setZ((float) 1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY >= 250) {
                        headerTitle.setVisibility(View.VISIBLE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            headerBack.setBackgroundColor(Color.argb((float) 0.6,0,0,0));
                        }
                    } else {
                        headerTitle.setVisibility(View.INVISIBLE);
                        headerBack.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            });
        }

        backdrop = findViewById(R.id.backdrop);
        Picasso.get().load(Constant.BASE_URL_IMAGE + movie.getBackdropPath()).into(backdrop);
        movieName = findViewById(R.id.titleName);
        movieName.setText(movie.getName());
        releaseDate = findViewById(R.id.releaseDate);
        releaseDate.setText("Release Date: " + movie.getReleaseDate());
        rate = findViewById(R.id.rate);
        rate.setText("Rate: " + movie.getVoteAverage() + "/10");
        genres = findViewById(R.id.genres);

        String types = "";
        for(int i = 0;i < movie.getListGenres().size();i++) {
            types += movie.getListGenres().get(i).getName();
            if (i != movie.getListGenres().size() - 1) {
                types += ", ";
            }
        }

        genres.setText("Genres: " + types);
        summary = findViewById(R.id.summary);
        summary.setText(movie.getDescription());

        viewListTrailers = findViewById(R.id.listTrailers);
        viewListTrailers.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieDetail.this, RecyclerView.HORIZONTAL,false);
        viewListTrailers.setLayoutManager(layoutManager);

        viewListReview = findViewById(R.id.listReviews);
        viewListReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(MovieDetail.this, RecyclerView.VERTICAL, false);
        viewListReview.setLayoutManager(layoutManager3);

        viewListSimilar = findViewById(R.id.listSimilar);
        viewListSimilar.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(MovieDetail.this, RecyclerView.HORIZONTAL,false);
        viewListSimilar.setLayoutManager(layoutManager2);

    }

    public void getTrailers() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseTrailer> call = service.getTrailers(
                movie.getId(),
                Constant.API_KEY,
                Constant.en
        );
        call.enqueue(new Callback<ObjectResponseTrailer>() {
            @Override
            public void onResponse(Call<ObjectResponseTrailer> call, Response<ObjectResponseTrailer> response) {
                ArrayList<TrailerData> listResults = response.body().getResults();
                for(TrailerData trailerData : listResults){
                    listTrailers.add(new Trailer(
                        movie.getBackdropPath(),
                        Constant.URL_YOUTUBE + trailerData.getKey(),
                        trailerData.getName()
                    ));
                }
                ListTrailerAdapter trailerAdapter = new ListTrailerAdapter(listTrailers, MovieDetail.this);
                viewListTrailers.setAdapter(trailerAdapter);
            }

            @Override
            public void onFailure(Call<ObjectResponseTrailer> call, Throwable t) {

            }
        });
    }

    public void getReviews() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseReview> call = service.getReviews(
                movie.getId(),
                Constant.API_KEY,
                Constant.en
        );
        call.enqueue(new Callback<ObjectResponseReview>() {
            @Override
            public void onResponse(Call<ObjectResponseReview> call, Response<ObjectResponseReview> response) {
                ArrayList<ReviewData> listResults = response.body().getResults();
                for(ReviewData reviewData : listResults){
                    listReviews.add(new UserReview(
                        new User(0, reviewData.getAuthor(),null,null),
                        reviewData.getContent()
                    ));
                }
                ListReviewAdapter listReviewAdapter = new ListReviewAdapter(listReviews, MovieDetail.this);
                viewListReview.setAdapter(listReviewAdapter);
            }

            @Override
            public void onFailure(Call<ObjectResponseReview> call, Throwable t) {

            }
        });
    }

    public void getSimilar() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseMovie> call = service.getSimilar(
                movie.getId(),
                Constant.API_KEY,
                Constant.en
        );
        call.enqueue(new Callback<ObjectResponseMovie>() {
            @Override
            public void onResponse(Call<ObjectResponseMovie> call, Response<ObjectResponseMovie> response) {
                ArrayList<MovieData> listResults = response.body().getResults();
                for(MovieData movieData : listResults){
                    ArrayList<Genres> list = new ArrayList<>();
                    for(int id : movieData.getGenre_ids()) {
                        for (int i = 0; i < GetListGenres.getListGenres().size(); i++) {
                            if (id == GetListGenres.getListGenres().get(i).getId()) list.add(GetListGenres.getListGenres().get(i));
                        }
                    }
                    listSimilar.add(new Movie(
                            movieData.getTitle(), movieData.getOverview(), movieData.getId(), movieData.getVote_average(), movieData.getPoster_path(),
                            movieData.getBackdrop_path(), movieData.getRelease_date(),null, list,null,null
                    ));
                }
                ListSimilarAdapter similarAdapter = new ListSimilarAdapter(listSimilar, MovieDetail.this);
                viewListSimilar.setAdapter(similarAdapter);
            }

            @Override
            public void onFailure(Call<ObjectResponseMovie> call, Throwable t) {

            }
        });
    }
}
