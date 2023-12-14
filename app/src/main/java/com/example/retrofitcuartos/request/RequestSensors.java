package com.example.retrofitcuartos.request;

import com.example.retrofitcuartos.models.Sensores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestSensors {
    @GET("/Adafruit/recibirdatos/{idcuarto}")
    Call<List<Sensores>> getSensores(@Path("idcuarto")String idcuarto);
}