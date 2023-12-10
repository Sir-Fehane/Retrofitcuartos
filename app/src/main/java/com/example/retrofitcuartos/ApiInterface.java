package com.example.retrofitcuartos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/api/recibir-datos")
    Call<List<Cuartos>> getCuartos();
}
