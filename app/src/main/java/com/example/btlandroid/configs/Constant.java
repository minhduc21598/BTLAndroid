package com.example.btlandroid.configs;

import android.content.res.Resources;

public class Constant {

    // For Api
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";
    public static final String URL_YOUTUBE = "https://www.youtube.com/watch?v=";
    public static final String API_KEY = "3843fccecb49c3242f778ddad3d50c3b";
    public static final String en = "en-US";
    public static final String vi = "vi-VN";

    // For layout settings
    public static final String [] LIST_SORT_TYPE = new String[] {"Most Popular", "Highest Rates", "Now Playing", "Upcoming"};
    public static final int WIDTH_SCREEN = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int HEIGHT_SCREEN = Resources.getSystem().getDisplayMetrics().heightPixels;
}
