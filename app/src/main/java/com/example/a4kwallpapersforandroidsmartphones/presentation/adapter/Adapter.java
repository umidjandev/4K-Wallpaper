package com.example.a4kwallpapersforandroidsmartphones.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a4kwallpapersforandroidsmartphones.R;
import com.example.a4kwallpapersforandroidsmartphones.domain.model.ImageModel;
import com.example.a4kwallpapersforandroidsmartphones.presentation.activity.SetWallpaper;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {



    Context context;
    ArrayList<ImageModel> wallpaperlist;

    public Adapter(Context context, ArrayList<ImageModel> wallpaperlist) {
        this.context = context;
        this.wallpaperlist = wallpaperlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context)
                .load(wallpaperlist
                        .get(position)
                        .getSrc()
                        .getOriginal())
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(e-> {
                Intent intent = new Intent(context, SetWallpaper.class);
                intent.putExtra("image",wallpaperlist.get(position).getSrc().getOriginal());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return wallpaperlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);

        }
    }
}
