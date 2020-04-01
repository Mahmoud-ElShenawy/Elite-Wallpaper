package com.m.elshenawy.elite.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public class Photo extends RealmObject {
    @SerializedName("id")
    @PrimaryKey // this annotation come from realm
    private String id;
    @SerializedName("urls")
    private PhotoUrl url = new PhotoUrl();
    @SerializedName("user")
    private User user = new User();

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public PhotoUrl getUrl() {
        return url;
    }
    public void setUrl(PhotoUrl url) {
        this.url = url;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}