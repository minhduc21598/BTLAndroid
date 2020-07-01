package com.example.btlandroid.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;

import com.example.btlandroid.R;

public class LoadingProgress {

    private static ProgressDialog progressDialog;

    public static void show(Context context) {
        progressDialog = ProgressDialog.show(context,null,null,true,false);
        progressDialog.setContentView(R.layout.loading_screen);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hide(){
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
