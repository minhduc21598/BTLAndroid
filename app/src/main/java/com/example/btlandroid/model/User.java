package com.example.btlandroid.model;

public class User {

    private long idUser;
    private String fullName;
    private String avatar;
    private Account account;

    public User(long idUser, String fullName, String avatar, Account account) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.avatar = avatar;
        this.account = account;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
