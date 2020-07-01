package com.example.btlandroid.controller;

import android.content.Intent;

import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.Account;
import com.example.btlandroid.model.dataAPI.RequestLogin;
import com.example.btlandroid.model.dataAPI.RequestToken;
import com.example.btlandroid.utils.GetDataService;
import com.example.btlandroid.utils.PostDataService;
import com.example.btlandroid.view.Home;
import com.example.btlandroid.view.LoginActivity;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginAction {

    public LoginAction() {
    }

    private static String token;
    private static boolean success;

    public static boolean login(String username, String pass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL2)
                .build();
        PostDataService service = retrofit.create(PostDataService.class);

        final RequestLogin requestLogin = new RequestLogin(username, pass, getToken());

        Call<RequestToken> call = service.loginUser(Constant.API_KEY, requestLogin);

        call.enqueue(new Callback<RequestToken>() {
            @Override
            public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                RequestToken requestToken = response.body();
                if(requestToken.isSuccess()){
                    success = requestToken.isSuccess();
                }
            }

            @Override
            public void onFailure(Call<RequestToken> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return success;
    }

    public static String getToken() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL2)
                .build();

        GetDataService service = retrofit.create(GetDataService.class);

        Call<RequestToken> call = service.getToken(Constant.API_KEY);

        call.enqueue(new Callback<RequestToken>() {
            @Override
            public void onResponse(Call<RequestToken> call, Response<RequestToken> response) {
                RequestToken requestToken = response.body();
                token = requestToken.getRequest_token();
            }

            @Override
            public void onFailure(Call<RequestToken> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return token;
    }
}
