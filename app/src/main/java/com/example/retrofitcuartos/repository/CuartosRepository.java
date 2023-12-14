package com.example.retrofitcuartos.repository;

import com.example.retrofitcuartos.retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class CuartosRepository {
public Retrofit retrofit;
void setRetrofit(){
    retrofit = RetrofitClient.getRetrofitClient();
}


}
