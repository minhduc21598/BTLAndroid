package com.example.btlandroid.utils;

import com.example.btlandroid.model.Movie;

public class SaveCurrentMovie {

    public static Movie currentMovie;

    public static Movie getCurrentMovie() {
        return currentMovie;
    }

    public static void setCurrentMovie(Movie currentMovie) {
        SaveCurrentMovie.currentMovie = currentMovie;
    }
}
