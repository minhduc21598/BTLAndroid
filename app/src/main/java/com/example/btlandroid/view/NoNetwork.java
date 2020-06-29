package com.example.btlandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.btlandroid.R;
import com.example.btlandroid.utils.NetworkReceiver;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoNetwork extends AppCompatActivity {

    TextView retry;
    LinearLayout noNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_network);
        noNetwork = findViewById(R.id.noNetWork);

        retry = findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filter = new Intent(ConnectivityManager.CONNECTIVITY_ACTION);
                boolean noConnectivity = filter.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
                setContentView(R.layout.loading_screen);
                if (!noConnectivity) {
                    Intent intent = new Intent(NoNetwork.this, NetworkReceiver.currentContext.getClass());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
