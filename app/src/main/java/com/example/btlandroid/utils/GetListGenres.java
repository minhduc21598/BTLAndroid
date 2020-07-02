package com.example.btlandroid.utils;

import com.example.btlandroid.model.Genres;

import java.util.ArrayList;

public class GetListGenres {

    private static ArrayList<Genres> listGenres;

    public static ArrayList<Genres> getListGenres() {
        return listGenres;
    }

    public static void setListGenres(ArrayList<Genres> listGenres) {
        GetListGenres.listGenres = listGenres;
    }
}
