package com.m.elshenawy.elite.Adapeters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.m.elshenawy.elite.Activities.FullScreenPhoto;
import com.m.elshenawy.elite.Models.Photo;
import com.m.elshenawy.elite.R;
import com.m.elshenawy.elite.Utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// Created By Mahmoud El Shenawy (Email : Mr.Mahmoud.El.Shenawy@Gmail.com)

public class PhotosAdapter
        extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private final String TAG = PhotosAdapter.class.getSimpleName();
    private Context context;
    private List<Photo> photos;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;
    public PhotosAdapter(Context context, List<Photo> photos)
    {
        this.context = context;
        this.photos = photos;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        GlideApp
                .with(context)
                .load(photo.getUrl().getRegular())
                .override(600,600)
                .into(holder.photo);

    }
    @Override
    public int getItemCount() {
        return photos == null ? 0 : photos.size();
    }
    @Override
    public int getItemViewType(int position) {
        return (position == photos.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_photo_image)
        SquareImage photo;
        @BindView(R.id.item_photo_layout)
        FrameLayout frameLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.item_photo_layout)
        public void setFrameLayout(){
            int position = getAdapterPosition();
            String photoId = photos.get(position).getId();
            Intent intent = new Intent(context, FullScreenPhoto.class);
            intent.putExtra("photoId", photoId);
            context.startActivity(intent);
        }
    }
}