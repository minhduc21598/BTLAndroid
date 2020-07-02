package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.model.dataAPI.account.ObjectResponseLogin;
import com.example.btlandroid.model.dataAPI.account.ObjectResponseRequestToken;
import com.example.btlandroid.utils.GetDataService;
import com.example.btlandroid.utils.LoadingProgress;
import com.example.btlandroid.utils.PostDataService;
import com.example.btlandroid.utils.RetrofitClientInstance;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    LinearLayout loginLayout;
    EditText username, password;
    TextView btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginLayout = findViewById(R.id.loginLayout);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.linkToSignUp);

        loginLayout.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) LoginActivity.this.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(),0);
            if (username.isFocusable()) {
                username.clearFocus();
            }
            if (password.isFocusable()) {
                password.clearFocus();
            }
        });

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String uname = username.getText().toString().trim();
            String pass = password.getText().toString().trim();
            if(uname.length() == 0 || pass.length() == 0) {
                Toast.makeText(LoginActivity.this,"Do not leave username or password blank!", Toast.LENGTH_SHORT).show();
            } else {
                login(uname, pass);
            }
        });
    }

    public void login(String username, String pass) {
        LoadingProgress.show(LoginActivity.this);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ObjectResponseRequestToken> call = service.getToken(Constant.API_KEY);
        call.enqueue(new Callback<ObjectResponseRequestToken>() {
            @Override
            public void onResponse(Call<ObjectResponseRequestToken> call, Response<ObjectResponseRequestToken> response) {
                PostDataService service = RetrofitClientInstance.getRetrofitInstance().create(PostDataService.class);
                RequestBody requestBodyLogin = new MultipartBody
                        .Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("username", username)
                        .addFormDataPart("password", pass)
                        .addFormDataPart("request_token", response.body().getRequest_token())
                        .build();
                Call<ObjectResponseLogin> call1 = service.loginUser(Constant.API_KEY, requestBodyLogin);
                call1.enqueue(new Callback<ObjectResponseLogin>() {
                    @Override
                    public void onResponse(Call<ObjectResponseLogin> call1, Response<ObjectResponseLogin> response1) {
                        if(response1.code() == 200){
                            saveInfor(username, pass);
                            Intent intent = new Intent(LoginActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,"Wrong username or password. Please try again!", Toast.LENGTH_SHORT).show();
                        }
                        LoadingProgress.hide();
                    }

                    @Override
                    public void onFailure(Call<ObjectResponseLogin> call1, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ObjectResponseRequestToken> call, Throwable t) {

            }
        });
    }

    public void saveInfor(String username, String pass){
        SharedPreferences.Editor editor = getSharedPreferences("InforAccount", MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("username", username);
        editor.putString("password", pass);
        editor.commit();
    }

}