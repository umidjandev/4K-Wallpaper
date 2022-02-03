package com.example.a4kwallpapersforandroidsmartphones.data;

import static com.example.a4kwallpapersforandroidsmartphones.data.ApiUtilities.API;

import com.example.a4kwallpapersforandroidsmartphones.domain.model.SearchModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    String BASE_URL ="https://api.pexels.com/v1/";

    @Headers("Authorization: "+ API)
    @GET("curated")
    Call<SearchModel> getImage(
            @Query("page") int page,
            @Query("per_page") int per_page
    );

//    @Headers("Authorization: "+ API)
//    @GET("curated")
//    Call<SearchModel> setImage(
//            @Query("page") int total_results
//    );

    @Headers("Authorization: "+ API)
    @GET("search")
    Call<SearchModel> getSearchImage(
            @Query("query") String query,
            @Query("page") int page,
            @Query("per_page") int per_page
    );


}
