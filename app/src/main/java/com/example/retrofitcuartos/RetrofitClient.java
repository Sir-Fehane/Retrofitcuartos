package com.example.retrofitcuartos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL ="http://54.161.51.54/api/auth/";
    private static Retrofit retrofit = null;
    public  static ApiInterface getRetrofitClient()
    {
        if(retrofit ==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
