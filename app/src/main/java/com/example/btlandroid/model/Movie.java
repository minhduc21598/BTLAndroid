package com.example.btlandroid.model;

import java.util.ArrayList;
import java.util.Date;

public class Movie {

    private String name;
    private String description;
    private int id;
    private float voteAverage;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private ArrayList<UserReview> listReviews;
    private ArrayList<Genres> listGenres;
    private ArrayList<Trailer> listTrailers;
    private ArrayList<Movie> listSimilar;

    public Movie(String name, String description, int id, float voteAverage, String posterPath, String backdropPath, String releaseDate, ArrayList<UserReview> listReviews, ArrayList<Genres> listGenres, ArrayList<Trailer> listTrailers, ArrayList<Movie> listSimilar) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.listReviews = listReviews;
        this.listGenres = listGenres;
        this.listTrailers = listTrailers;
        this.listSimilar = listSimilar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<UserReview> getListReviews() {
        return listReviews;
    }

    public void setListReviews(ArrayList<UserReview> listReviews) {
        this.listReviews = listReviews;
    }

    public ArrayList<Genres> getListGenres() {
        return listGenres;
    }

    public void setListGenres(ArrayList<Genres> listGenres) {
        this.listGenres = listGenres;
    }

    public ArrayList<Trailer> getListTrailers() {
        return listTrailers;
    }

    public void setListTrailers(ArrayList<Trailer> listTrailers) {
        this.listTrailers = listTrailers;
    }

    public ArrayList<Movie> getlistSimilar() {
        return listSimilar;
    }

    public void setlistSimilar(ArrayList<Movie> listSimilar) {
        this.listSimilar = listSimilar;
    }
}
