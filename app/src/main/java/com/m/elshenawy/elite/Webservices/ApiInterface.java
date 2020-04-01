package com.m.elshenawy.elite.Webservices;

import com.m.elshenawy.elite.Models.Collection;
import com.m.elshenawy.elite.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos(
            @Query("per_page") Integer per_page);
    @GET("collections/featured")
    Call<List<Collection>> getCollections(
            @Query("per_page") int per_page);
    @GET("collections/{id}/photos")
    Call<List<Photo>> getPhotoOfCollection(@Path("id") int id,
                                           @Query("per_page") int per_page);
    @GET("photos/{id}")
    Call<Photo> getPhoto(@Path("id") String id);
}