package com.videosimplemaster.hdplaydownload.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.videosimplemaster.hdplaydownload.R;
import com.videosimplemaster.hdplaydownload.dummy.VideoPlayerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


import java.util.ArrayList;

public class VideosAdapterNayaDownloader extends RecyclerView.Adapter<VideosAdapterNayaDownloader.ViewHolder> {

    ArrayList<Integer> videos;
    Activity activity;

    public VideosAdapterNayaDownloader(Activity activity, ArrayList<Integer> videos) {
        this.videos = videos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(activity)
                .load(videos.get(position)).addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        holder.rlProgress.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        holder.rlProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .thumbnail(0.1f)
                .into(holder.ivThumbnail);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VideoPlayerActivity.class);
                intent.putExtra("video", videos.get(position));
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rlProgress;
        ImageView ivThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlProgress = itemView.findViewById(R.id.rlProgress);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }
    }


}
