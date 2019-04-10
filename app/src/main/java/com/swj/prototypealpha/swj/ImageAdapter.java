package com.swj.prototypealpha.swj;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.swj.prototypealpha.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;

    private List<Picture> pictures;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        ImageView imageView;

        public ViewHolder(View view)
        {
            super(view);
            cardView = (CardView)view;
            imageView = view.findViewById(R.id.image);
        }
    }

    public ImageAdapter(List<Picture> pictures)
    {
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(context == null)
        {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int i) {
        Picture picture = pictures.get(i);
        viewHolder.imageView.setImageBitmap(picture.getImageID());

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}
