package com.example.retrofitcuartos.request;

import com.example.retrofitcuartos.models.Usuarios;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserInterface {
    @GET("/auth/profile")
    Call<Usuarios> obtenerUsuario();
}
