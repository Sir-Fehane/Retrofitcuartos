package com.example.retrofitcuartos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofitcuartos.R;
import com.example.retrofitcuartos.UsuariosVista;
import com.example.retrofitcuartos.adapters.CuartosAdapter;
import com.example.retrofitcuartos.models.Cuartos;
import com.example.retrofitcuartos.request.ApiInterface;
import com.example.retrofitcuartos.request.RequestSensors;
import com.example.retrofitcuartos.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc;
    ProgressBar pb;
    LinearLayoutManager llm;
    CuartosAdapter Ca;
    List<Cuartos> cuartosList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.recyclerView);
        pb = findViewById(R.id.progressBar);
        llm = new LinearLayoutManager(this);
        rc.setLayoutManager(llm);
        Ca = new CuartosAdapter(cuartosList);
        rc.setAdapter(Ca);
        fetchCuartos();
    }

    private void fetchCuartos() {
        String key = " Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vNTQuMTYxLjUxLjU0L2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyNjQxNTA0LCJleHAiOjE3MDI2NDUxMDQsIm5iZiI6MTcwMjY0MTUwNCwianRpIjoibnpodTk0RDc0Z2oweWMyNSIsInN1YiI6IjEiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.xKl5Wud6TBRPNjdlTfGtHYQsjrNHD1olz9LnMR1GWlQ";
        pb.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = RetrofitClient.getRetrofitClient().create(ApiInterface.class);
        Call<List<Cuartos>> call = apiInterface.getCuartos(key);
                call.enqueue(new Callback<List<Cuartos>>() {
            @Override
            public void onResponse(Call<List<Cuartos>> call, Response<List<Cuartos>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    cuartosList.addAll(response.body());
                    Ca.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<Cuartos>> call, Throwable t) {
                pb.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
    public void ayuda()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, UsuariosVista.class);
                startActivity(intent);

            }
        }, 500); // 5000 milisegundos (5 segundos)
    }
}

