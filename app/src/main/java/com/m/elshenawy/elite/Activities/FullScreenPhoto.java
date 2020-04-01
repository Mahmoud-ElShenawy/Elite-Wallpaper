package com.m.elshenawy.elite.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.m.elshenawy.elite.Adapeters.GlideApp;
import com.m.elshenawy.elite.Models.Photo;
import com.m.elshenawy.elite.R;
import com.m.elshenawy.elite.Utils.Functions;
import com.m.elshenawy.elite.Utils.RealmController;
import com.m.elshenawy.elite.Webservices.ApiInterface;
import com.m.elshenawy.elite.Webservices.ServiceGenerator;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public class FullScreenPhoto extends AppCompatActivity {
    @BindView(R.id.activity_fullscreen_photo_photo)
    ImageView fullScreenPhoto;
    @BindView(R.id.activity_fullscreen_photo_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.activity_fullscreen_photo_favorite)
    FloatingActionButton fabFavorite;
    @BindView(R.id.activity_fullscreen_photo_wallpaper)
    FloatingActionButton fabWallpaper;
    @BindView(R.id.activity_fullscreen_photo_menu)
    FloatingActionMenu fabMenu;
    @BindView(R.id.activity_fullscreen_photo_username)
    TextView userName;
    @BindDrawable(R.drawable.ic_check_favorite)
    Drawable icFavorite;
    @BindDrawable(R.drawable.ic_check_favorited)
    Drawable icFavorited;
    private Bitmap photoBitmap;
    private RealmController realmController;
    private Unbinder unbinder;
    private Photo photo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_photo);
        // Remove ActionBar and StatusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        String photoId = intent.getStringExtra("photoId");
        getPhoto(photoId);
        realmController = new RealmController();
        if (realmController.isPhotoExist(photoId)){
            fabFavorite.setImageDrawable(icFavorited);
        }
    }
    private void getPhoto(String id){
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<Photo> call = apiInterface.getPhoto(id);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                if (response.isSuccessful()){
                    photo = response.body();
                    updateUI(photo);
                }
            }
            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });

    }
    private void updateUI(Photo photo) {
        try {
            userName.setText(photo.getUser().getUsername());
            GlideApp
                    .with(FullScreenPhoto.this)
                    .load(photo.getUser().getProfileImage().getSmall())
                    .into(userAvatar);
            GlideApp
                    .with(FullScreenPhoto.this)
                    .asBitmap()
                    .load(photo.getUrl().getRegular())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            fullScreenPhoto.setImageBitmap(resource);
                            photoBitmap = resource;
                        }
                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @OnClick(R.id.activity_fullscreen_photo_favorite)
    public void setFabFavorite(){
        if (realmController.isPhotoExist(photo.getId())){
            realmController.deletePhoto(photo);
            fabFavorite.setImageDrawable(icFavorite);
            Toast.makeText(FullScreenPhoto.this, "Remove Favorite!", Toast.LENGTH_SHORT).show();
        }else {
            realmController.savePhoto(photo);
            fabFavorite.setImageDrawable(icFavorited);
            Toast.makeText(FullScreenPhoto.this, "Favorite!", Toast.LENGTH_SHORT).show();
        }
        fabMenu.close(true);
    }

    @OnClick(R.id.activity_fullscreen_photo_wallpaper)
    public void setFabWallpaper(){
        if (photoBitmap != null) {
            if (Functions.setWallpaper(FullScreenPhoto.this,photoBitmap)){
                Toast.makeText(FullScreenPhoto.this,"Set Wallpaper Successfully!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(FullScreenPhoto.this,"Set Wallpaper Fail!", Toast.LENGTH_SHORT).show();
            }
        }
        fabMenu.close(true);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}