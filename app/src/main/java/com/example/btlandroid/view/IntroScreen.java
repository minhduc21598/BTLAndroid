package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btlandroid.R;

public class IntroScreen extends AppCompatActivity {

    Button btnTest;
    ImageView logoApp;
    TextView titleApp;
    Animation appear, scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_screen);
        init();
    }

    public void init() {
        logoApp = findViewById(R.id.logoApp);
        titleApp = findViewById(R.id.titleApp);
        appear = AnimationUtils.loadAnimation(IntroScreen.this, R.anim.anim_appear);
        scale = AnimationUtils.loadAnimation(IntroScreen.this, R.anim.anim_rotate);
        titleApp.startAnimation(appear);
        logoApp.startAnimation(scale);

        new android.os.Handler().postDelayed(
            new Runnable() {
                @Override
                public void run() {
                    loadLogin();
                }
            },2500
        );
    }

    public void loadLogin(){

        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String user = prefs.getString("username", "");
        String pass = prefs.getString("pass", "");
        if(user.equals("") || pass.equals("")){
            Intent intent = new Intent(IntroScreen.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(IntroScreen.this, Home.class);
            startActivity(intent);
        }
    }

}
