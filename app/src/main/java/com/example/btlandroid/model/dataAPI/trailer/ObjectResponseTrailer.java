package com.example.btlandroid.model.dataAPI.trailer;

import com.example.btlandroid.model.dataAPI.trailer.TrailerData;

import java.util.ArrayList;

public class ObjectResponseTrailer {

    private int id;
    private ArrayList<TrailerData> results;

    public ObjectResponseTrailer(int id, ArrayList<TrailerData> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TrailerData> getResults() {
        return results;
    }

    public void setResults(ArrayList<TrailerData> results) {
        this.results = results;
    }
}
