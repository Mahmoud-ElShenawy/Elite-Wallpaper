package com.m.elshenawy.elite.Utils;

import com.m.elshenawy.elite.Fragments.PhotoFragment;
import com.m.elshenawy.elite.Models.Photo;

import java.util.List;

import io.realm.Realm;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public class RealmController {
    private final Realm realm;
    private final static String TAG = PhotoFragment.class.getSimpleName();
    public RealmController(){
        realm = Realm.getDefaultInstance();
    }
    public void savePhoto(Photo photo){
        realm.beginTransaction();
        realm.copyToRealm(photo);
        realm.commitTransaction();
    }
    public void deletePhoto(Photo photo){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Photo resultPhoto = realm.where(Photo.class).equalTo("id",
                        photo.getId()).findFirst();
                resultPhoto.deleteFromRealm();
            }
        });
    }
    public boolean isPhotoExist(String photoId){
        Photo resultPhoto = realm.where(Photo.class).equalTo("id",
                photoId).findFirst();
        if (resultPhoto == null)
            return false;
        return true;
    }
    public List<Photo> getPhotos(){
        return realm.where(Photo.class).findAll();
    }
}