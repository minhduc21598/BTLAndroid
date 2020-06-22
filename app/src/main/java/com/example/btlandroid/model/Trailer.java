package com.example.btlandroid.model;

public class Trailer {

    private String thumbnail;
    private String url;

    public Trailer(String thumbnail, String url) {
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
