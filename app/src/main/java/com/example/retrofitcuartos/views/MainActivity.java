package com.example.retrofitcuartos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofitcuartos.R;
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
        String key = " Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vNTQuMTYxLjUxLjU0L2FwaS9hdXRoL2xvZ2luIiwiaWF0IjoxNzAyNTk1OTU0LCJleHAiOjE3MDI1OTk1NTQsIm5iZiI6MTcwMjU5NTk1NCwianRpIjoiV3VoVzRkMWJOeWk5R2JFOSIsInN1YiI6IjEiLCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3In0.RWGuw9r2CBfy4ULRgU62Kaave9R6QaRsAg76QGdxEOo";
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
}