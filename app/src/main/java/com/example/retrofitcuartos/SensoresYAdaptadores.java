package com.example.retrofitcuartos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensoresYAdaptadores extends AppCompatActivity {
    RecyclerView rcv;
    List<Sensores> sensList = new ArrayList<>();
    SensorsAdapter sns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_yadaptadores);
        rcv = findViewById(R.id.sensorsitem);
        sns = new SensorsAdapter(sensList);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(sns);
        rcv.setHasFixedSize(true);
        fetchsensors();
    }

    private void fetchsensors() {
        RetrofitClient.getRetrofitClient().getSensores().enqueue(new Callback<List<Sensores>>() {
            @Override
            public void onResponse(Call<List<Sensores>> call, Response<List<Sensores>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    sensList.addAll(response.body());
                    sns.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<Sensores>> call, Throwable t) {
                Toast.makeText(SensoresYAdaptadores.this, t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}