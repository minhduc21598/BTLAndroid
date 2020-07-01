package com.example.btlandroid.utils;

import com.example.btlandroid.model.dataAPI.MovieData;
import com.example.btlandroid.model.dataAPI.ObjectResponseMovie;
import com.example.btlandroid.model.dataAPI.RequestToken;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("{type}")
    Call<ObjectResponseMovie> getMovieList(@Path("type") String type, @Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("authentication/token/new")
    Call<RequestToken> getToken(@Query("api_key") String api_key);

}
