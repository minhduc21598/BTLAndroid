package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.btlandroid.R;
import com.example.btlandroid.configs.Constant;
import com.example.btlandroid.utils.NetworkReceiver;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    NetworkReceiver networkReceiver = new NetworkReceiver();
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

        loginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) LoginActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);
                if (username.isFocusable()) {
                    username.clearFocus();
                }
                if (password.isFocusable()) {
                    password.clearFocus();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                String url = Constant.BASE_URL.concat("authentication/token/new?api_key=").concat(Constant.API_KEY);
//                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            String token = response.getString("request_token");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//                queue.add(jsonRequest);
                Intent intent = new Intent(LoginActivity.this, Home.class);
                startActivity(intent);
            }
        });
//        new NetworkReceiver().onReceive(LoginActivity.this, new Intent());
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

    public void loginUser(String user, String pass, String token) {
        JSONObject json = new JSONObject();
        try {
            json.put("username", user);
            json.put("password", pass);
            json.put("request_token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}