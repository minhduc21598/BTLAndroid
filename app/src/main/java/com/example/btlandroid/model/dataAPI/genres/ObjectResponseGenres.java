package com.example.btlandroid.model.dataAPI.genres;

import com.example.btlandroid.model.Genres;

import java.util.ArrayList;

public class ObjectResponseGenres {

    private ArrayList<Genres> genres;

    public ObjectResponseGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }

    public ArrayList<Genres> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genres> genres) {
        this.genres = genres;
    }
}
