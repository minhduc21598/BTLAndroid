package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btlandroid.R;

public class MainActivity extends AppCompatActivity {

    Button btnTest;
    ImageView logoApp;
    TextView titleApp;
    Animation appear, scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        logoApp = findViewById(R.id.logoApp);
        titleApp = findViewById(R.id.titleApp);
        appear = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_appear);
        scale = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_rotate);
        titleApp.startAnimation(appear);
        logoApp.startAnimation(scale);

        new android.os.Handler().postDelayed(
            new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            },2500
        );
    }

}
