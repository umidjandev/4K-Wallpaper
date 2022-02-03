package com.example.a4kwallpapersforandroidsmartphones.presentation.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a4kwallpapersforandroidsmartphones.R;

import java.io.IOException;

public class SetWallpaper extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    Button set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        set = findViewById(R.id.set);
        imageView = findViewById(R.id.finalImage);
        intent = getIntent();

        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);
        set.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                try {
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//                    wallpaperManager.setBitmap(bitmap);
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
//                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                }
                catch(IOException e)
                {
                e.printStackTrace();
                }


            }
        });

    }
}