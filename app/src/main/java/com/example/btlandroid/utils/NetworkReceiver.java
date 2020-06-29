package com.example.btlandroid.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.btlandroid.view.NoNetwork;

public class NetworkReceiver extends BroadcastReceiver {

    public static Context currentContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY,false
            );
            if (noConnectivity) {
                context.startActivity(new Intent(context, NoNetwork.class));
                currentContext = context;
            }
        }
    }
}
