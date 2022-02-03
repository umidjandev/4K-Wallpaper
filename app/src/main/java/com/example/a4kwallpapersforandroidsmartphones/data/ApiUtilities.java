package com.example.a4kwallpapersforandroidsmartphones.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {
    private static Retrofit retrofit = null;
    public static final String API = "563492ad6f917000010000011ecd2c13bbb340b99d7e8066bbbb22ab";


    public static ApiInterface getApiInterface(){

        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory
                                    .create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);


    }




}
