package com.m.elshenawy.elite.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public class User extends RealmObject {
    @SerializedName("id")
    private String id;
    @SerializedName("username")
    private String username;
    @SerializedName("profile_image")
    private ProfileImage profileImage = new ProfileImage();
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public ProfileImage getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }
}