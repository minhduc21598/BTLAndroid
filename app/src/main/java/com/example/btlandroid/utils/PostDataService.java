package com.example.btlandroid.utils;

import com.example.btlandroid.model.dataAPI.account.ObjectResponseLogin;
import com.example.btlandroid.model.dataAPI.account.RequestBodyLogin;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface PostDataService {

    @POST("authentication/token/validate_with_login")
    Call<ObjectResponseLogin> loginUser(@Query("api_key") String api_key, @Body RequestBody requestBodyLogin);

}
