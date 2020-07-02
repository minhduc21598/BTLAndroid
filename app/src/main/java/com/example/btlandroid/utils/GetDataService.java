package com.example.btlandroid.utils;

import com.example.btlandroid.model.dataAPI.genres.ObjectResponseGenres;
import com.example.btlandroid.model.dataAPI.movie.ObjectResponseMovie;
import com.example.btlandroid.model.dataAPI.review.ObjectResponseReview;
import com.example.btlandroid.model.dataAPI.trailer.ObjectResponseTrailer;
import com.example.btlandroid.model.dataAPI.account.ObjectResponseRequestToken;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("movie/{type}")
    Call<ObjectResponseMovie> getMovieList(@Path("type") String type, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("genre/movie/list")
    Call<ObjectResponseGenres> getAllGenres(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<ObjectResponseTrailer> getTrailers(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/reviews")
    Call<ObjectResponseReview> getReviews(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/similar")
    Call<ObjectResponseMovie> getSimilar(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("search/movie")
    Call<ObjectResponseMovie> getSearch(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String keyword, @Query("page") int page);

    @GET("authentication/token/new")
    Call<ObjectResponseRequestToken> getToken(@Query("api_key") String apiKey);
}
