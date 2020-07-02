package com.example.btlandroid.model;

import java.io.Serializable;

public class Trailer implements Serializable {

    private String thumbnail;
    private String url;
    private String name;

    public Trailer(String thumbnail, String url, String name) {
        this.thumbnail = thumbnail;
        this.url = url;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
