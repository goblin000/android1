package com.example.lesson1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ImageViewHolder> {

    private int []images;

    public RecycleAdapter(int []images){
        this.images = images;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view);


        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

//        int image_id = images[position];
//        holder.album.setImageResource(image_id);
//        holder.albumTitle.setText("Image: "+ position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }


    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView album;
        TextView albumTitle;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            album = itemView.findViewById(R.id.album);
            albumTitle = itemView.findViewById(R.id.albumTitle);
        }
    }
}
