package com.m.elshenawy.elite.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public class ProfileImage extends RealmObject {
    @SerializedName("small")
    private String small;
    @SerializedName("medium")
    private String medium;
    public String getSmall() {
        return small;
    }
    public void setSmall(String small) {
        this.small = small;
    }
    public String getMedium() {
        return medium;
    }
    public void setMedium(String medium) {
        this.medium = medium;
    }
}