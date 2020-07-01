package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.btlandroid.R;
import com.example.btlandroid.controller.LoginAction;

import org.json.JSONException;
import org.json.JSONObject;

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
                LoginAction action = new LoginAction();
                if(action.login(username.getText().toString().trim(), password.getText().toString().trim())){

                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void saveInfor(String username, String pass){
        SharedPreferences.Editor editor = getSharedPreferences("Data", MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("username", username);
        editor.putString("password", pass);
        editor.commit();
    }

}