package com.example.retrofitcuartos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitcuartos.R;
import com.example.retrofitcuartos.adapters.SensorsAdapter;
import com.example.retrofitcuartos.models.Cuartos;
import com.example.retrofitcuartos.models.Sensores;
import com.example.retrofitcuartos.request.RequestSensors;
import com.example.retrofitcuartos.request.SwitchChangeListener;
import com.example.retrofitcuartos.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SensoresYAdaptadores extends AppCompatActivity {
    RecyclerView rcv;
    List<Sensores> sensList = new ArrayList<>();
    SensorsAdapter sns;
    String idcuarto;
    String Title;
    String text;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_yadaptadores);
        Intent info = getIntent();
        String idcuarto = info.getStringExtra("id");
        rcv = findViewById(R.id.sensorsitem);
        sns = new SensorsAdapter(sensList, new SwitchChangeListener() {
            @Override
            public void onSwitchChanged(String feed_key, boolean isChecked) {

            }
        });
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(sns);
        rcv.setHasFixedSize(true);
        fetchSensors(idcuarto);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notif","ServSecurity", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"notif")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(Title)
                .setContentText(text);
        notification = builder.build();
        notificationManagerCompat = NotificationManagerCompat.from(this);
    }
    private void fetchSensors(String idcuartos) {
        RequestSensors requestSensors = RetrofitClient.getRetrofitClient().create(RequestSensors.class);
        Call<List<Sensores>> call = requestSensors.getSensores(idcuartos);
        call.enqueue(new Callback<List<Sensores>>() {
            @Override
            public void onResponse(Call<List<Sensores>> call, Response<List<Sensores>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    sensList = response.body();
                    sns.setSensorDataList(sensList);
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