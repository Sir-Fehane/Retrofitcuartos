package com.example.retrofitcuartos.repository;

import android.view.PixelCopy;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitcuartos.models.Sensores;
import com.example.retrofitcuartos.request.RequestSensors;
import com.example.retrofitcuartos.retrofit.RetrofitClient;

import java.util.List;
import java.util.concurrent.Semaphore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SensorsRepository {
    public Retrofit retrofit;
    void setRetrofit() {
        retrofit = RetrofitClient.getRetrofitClient();
    }
    public MutableLiveData<List<Sensores>> getSensors(String idcuarto) {
        setRetrofit();
        RequestSensors sensorsreq = retrofit.create(RequestSensors.class);
        Call<List<Sensores>> sensCall = sensorsreq.getSensores(idcuarto);
        MutableLiveData<List<Sensores>> mutableLiveData = new MutableLiveData<>();

        sensCall.enqueue(new Callback<List<Sensores>>() {
            @Override
            public void onResponse(Call<List<Sensores>> call, Response<List<Sensores>> response) {
                if (response.isSuccessful()) {
                    List<Sensores> senss = response.body();
                    mutableLiveData.setValue(senss);
                }
            }

            @Override
            public void onFailure(Call<List<Sensores>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
