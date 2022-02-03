package com.example.a4kwallpapersforandroidsmartphones.presentation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a4kwallpapersforandroidsmartphones.R;
import com.example.a4kwallpapersforandroidsmartphones.data.ApiUtilities;
import com.example.a4kwallpapersforandroidsmartphones.domain.model.ImageModel;
import com.example.a4kwallpapersforandroidsmartphones.domain.model.SearchModel;
import com.example.a4kwallpapersforandroidsmartphones.presentation.adapter.Adapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    Adapter adapter;
    EditText editText;
    ImageView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
            getSupportActionBar().hide();
            recyclerView= findViewById(R.id.recycleView);
            editText = findViewById(R.id.editText);
            search= findViewById(R.id.search);


            modelClassList = new ArrayList<>();
            recyclerView.setLayoutManager(new GridLayoutManager(this,3));
            recyclerView.setHasFixedSize(true);
            adapter = new Adapter(getApplicationContext(),modelClassList);
            recyclerView.setAdapter(adapter);
            findphotos();


            search.setOnClickListener(l -> {
                String query = editText.getText().toString().trim().toLowerCase();
                if (query.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter something", Toast.LENGTH_SHORT).show();
                }
                else {
                    getsearchimage(query);
                }
            });

    }

        private void getsearchimage(String query) {
            ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
                @Override
                public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                    modelClassList.clear();
                    if (response.isSuccessful()){
                        modelClassList.addAll(response.body().getPhotos());
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<SearchModel> call, Throwable t) {

                }
            });

        }

        private void findphotos() {
        getsearchimage("fantasy");
//            ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
//                @Override
//                public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
//                    if (response.isSuccessful()){
//                        modelClassList.addAll(response.body().getPhotos());
//                        adapter.notifyDataSetChanged();
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<SearchModel> call, Throwable t) {
//
//                }
//            });
//
        }


}
