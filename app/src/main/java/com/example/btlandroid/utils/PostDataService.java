package com.example.btlandroid.utils;

import com.example.btlandroid.model.Account;
import com.example.btlandroid.model.dataAPI.RequestLogin;
import com.example.btlandroid.model.dataAPI.RequestToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PostDataService {

    @POST("authentication/token/validate_with_login")
    Call<RequestToken> loginUser(@Query("api_key") String api_key, @Body RequestLogin login);

}
