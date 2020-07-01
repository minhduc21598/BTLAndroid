package com.example.btlandroid.model.dataAPI;

public class RequestToken {

    private boolean success;
    private String expires_at;
    private String request_token;

    public RequestToken(boolean success, String expires_at, String request_token) {
        this.success = success;
        this.expires_at = expires_at;
        this.request_token = request_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public String getRequest_token() {
        return request_token;
    }
}
