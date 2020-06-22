package com.example.btlandroid.model;

import java.util.ArrayList;
import java.util.Date;

public class Movie {

    private String name;
    private String description;
    private int id;
    private float voteAverage;
    private double popularity;
    private String posterPath;
    private String backdropPath;
    private Date releaseDate;
    private ArrayList<UserReview> listReviews;
    private ArrayList<Genres> listGenres;
    private ArrayList<Trailer> listTrailers;

    public Movie(String name, String description, int id, float voteAverage, double popularity, String posterPath, String backdropPath, Date releaseDate, ArrayList<UserReview> listReviews, ArrayList<Genres> listGenres, ArrayList<Trailer> listTrailers) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.listReviews = listReviews;
        this.listGenres = listGenres;
        this.listTrailers = listTrailers;
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

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
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
}
