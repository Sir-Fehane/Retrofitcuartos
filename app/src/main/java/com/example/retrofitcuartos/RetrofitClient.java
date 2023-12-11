package com.example.retrofitcuartos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL ="proyecto4to.cwzwdmivlhhv.us-east-1.rds.amazonaws.com";
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
