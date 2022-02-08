package com.example.a4kwallpapersforandroidsmartphones.presentation.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    int page = 1, perPage = 18;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recycleView);
        editText = findViewById(R.id.editText);
        search = findViewById(R.id.search);
        nestedScrollView = findViewById(R.id.nestedscroll);
        progressBar = findViewById(R.id.progress_bar);
        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(), modelClassList);
        recyclerView.setAdapter(adapter);
        findPhotos(page);


        search.setOnClickListener(l -> {
            search();
        });

        editText.setOnEditorActionListener((textView, action, keyEvent) -> {
            if (action == EditorInfo.IME_ACTION_DONE) {
                search();
                return false;
            }
            return true;
        });

        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY == v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight()){
                page++;
                progressBar.setVisibility(View.VISIBLE);
                findPhotos(page);

            }
        });

    }

    private void search() {
        String query = editText.getText().toString().trim().toLowerCase();
        if (query.isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter something", Toast.LENGTH_SHORT).show();
        }
        else {
            modelClassList.clear();
            getSearchImage(query,page);
        }
    }

    private void getSearchImage(String query, int page ) {
        ApiUtilities.getApiInterface().getSearchImage(query, page, perPage).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                if (response.isSuccessful()) {
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }

    private void findPhotos(int page) {
//        getSearchImage("fantasy",page,perPage);
            ApiUtilities.getApiInterface().getImage(page,perPage).enqueue(new Callback<SearchModel>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
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


}
