package com.example.btlandroid.model;

public class UserReview {

    private User user;
    private String userComment;

    public UserReview(User user, String userComment) {
        this.user = user;
        this.userComment = userComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }
}
