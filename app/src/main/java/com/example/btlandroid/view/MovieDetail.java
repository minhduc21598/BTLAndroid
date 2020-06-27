package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Movie;
import com.example.btlandroid.model.Trailer;
import com.example.btlandroid.model.User;
import com.example.btlandroid.model.UserReview;
import com.example.btlandroid.view.recycleview.ListReviewAdapter;
import com.example.btlandroid.view.recycleview.ListSimilarAdapter;
import com.example.btlandroid.view.recycleview.ListTrailerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetail extends AppCompatActivity {

    ScrollView scrollView;
    LinearLayout headerBack;
    RecyclerView viewListTrailers, viewListSimilar, viewListReview;
    ImageView back;
    ImageView backdrop;
    TextView movieName, releaseDate, rate, genres, summary, headerTitle;
    ArrayList<Trailer> listTrailers = new ArrayList<>();
    ArrayList<Movie> listSimilar = new ArrayList<Movie>();
    ArrayList<UserReview> listReviews = new ArrayList<>();

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
            null,
            "New Official Trailer"
        ));
        listTrailers.add(new Trailer(
                "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                null,
                "Sonic The Hedgehog (2020) - \\\"Classic\\\" - Paramount Pictures"
        ));
        listTrailers.add(new Trailer(
                "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
                null,
                "Sonic The Hedgehog (2020) - Big Game Spot - Paramount Pictures"
        ));
        ListTrailerAdapter trailerAdapter = new ListTrailerAdapter(listTrailers, MovieDetail.this);
        viewListTrailers.setAdapter(trailerAdapter);

        viewListSimilar = findViewById(R.id.listSimilar);
        viewListSimilar.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(MovieDetail.this, RecyclerView.HORIZONTAL,false);
        viewListSimilar.setLayoutManager(layoutManager2);
        listSimilar.add(new Movie(
            "Britt-Marie Was Here", null, 0, 0, "/1Duc3EBiegywczxTWekvy03HWai.jpg", null, null, null, null, null, null
        ));
        listSimilar.add(new Movie(
            "Wasp Network", null, 0, 0, "/fOvqEunubL3wPskvtk2Ficfl0pH.jpg", null, null, null, null, null, null
        ));
        listSimilar.add(new Movie(
            "Britt-Marie Was Here", null, 0, 0, "/1Duc3EBiegywczxTWekvy03HWai.jpg", null, null, null, null, null, null
        ));
        listSimilar.add(new Movie(
            "Wasp Network", null, 0, 0, "/fOvqEunubL3wPskvtk2Ficfl0pH.jpg", null, null, null, null, null, null
        ));
        ListSimilarAdapter similarAdapter = new ListSimilarAdapter(listSimilar, MovieDetail.this);
        viewListSimilar.setAdapter(similarAdapter);

        viewListReview = findViewById(R.id.listReviews);
        viewListReview.setHasFixedSize(true);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(MovieDetail.this, RecyclerView.VERTICAL, false);
        viewListReview.setLayoutManager(layoutManager3);
        listReviews.add(new UserReview(
            new User(
                0,
                "kinglargo",
                null,
                null
            ),
            "I had high expectation for Sonic, simply because I love the concept of \\\"super-speed\\\" but it didn't deliver. The latest trailer had all the good scenes. A lot of scenes involving super-speed are incoherent and made no sense. You cannot have a missile frozen in time and a the same time a button push going faster (that's just one example). Jim Carrey nailed the role as Eggman.\\r\\n\\r\\nhttps://wefishmovies.herokuapp.com/views/movie-info.php?tmdb_id=454626"
        ));
        listReviews.add(new UserReview(
                new User(
                    0,
                    "Louisa Moore - Screen Zealots",
                    null,
                    null
                ),
                "In what could only be a dead-of-winter miracle in the Upside Down, I just saw an inspired-by a video game movie that wasn’t absolutely terrible. There is nothing about “Sonic the Hedgehog” that seems like it will be watchable, much less actually good, but I’ve been proven wrong by this lively, wacky, and campy family film. There’s oodles of fun to be had here by both adults and kids.\\r\\n\\r\\nThis live action adventure comedy is based on the blockbuster SEGA game that centers around a speedy bright blue hedgehog named Sonic. Basing a movie on a flimsy gaming character will be ill-advised at least 95% of the time, but this movie finds the ideal balance between a lively (if basic) original story and well-placed Easter eggs for fans. Here, Sonic (voiced by Ben Schwartz) has a series of misadventures on Earth with his newfound human friend Tom (James Marsden), a small-town police officer. The two have to work together to stop the villainous Dr. Robotnik (Jim Carrey) from capturing Sonic for his evil experiments.\\r\\n\\r\\nThe themes of friendship and loneliness are at play, and the cast does a great job keeping things fun. Marsden is ridiculously likable and goofy as an everyman, and Schwartz brings an empathy that carries the majority of the film with his voice performance. Best of all is Carrey, hamming it up with a manic stamina that’ll remind you why you always loved him in the first place.\\r\\n\\r\\nThere are plenty of action scenes and rude humor (and a couple of strange, over-the-top scenes that read as advertisements for Olive Garden), which are all entertaining enough to recommend “Sonic” for a fun family night out at the movies."
        ));
        listReviews.add(new UserReview(
                new User(
                    0,
                    "teamelitedev",
                    null,
                    null
                ),
                "The world needed a hero -- it got a hedgehog. Powered with incredible speed, Sonic embraces his new home on Earth -- until he accidentally knocks out the power grid, sparking the attention of uncool evil genius Dr. Robotnik. Now, it's supervillain vs. supersonic in an all-out race across the globe to stop Robotnik from using Sonic's unique power to achieve world domination."
        ));
        ListReviewAdapter listReviewAdapter = new ListReviewAdapter(listReviews, MovieDetail.this);
        viewListReview.setAdapter(listReviewAdapter);
    }
}
