package com.example.retrofitcuartos.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.retrofitcuartos.R;
import com.example.retrofitcuartos.adapters.SensorsAdapter;
import com.example.retrofitcuartos.models.Cuartos;
import com.example.retrofitcuartos.models.SensorList;
import com.example.retrofitcuartos.models.Sensores;
import com.example.retrofitcuartos.request.RequestSensors;
import com.example.retrofitcuartos.request.SwitchChangeListener;
import com.example.retrofitcuartos.retrofit.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Array;
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
    private int notifID = 1;
    SwitchChangeListener switchChangeListener;
    NotificationManagerCompat notificationManagerCompat;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores_yadaptadores);
        Intent info = getIntent();
        idcuarto = info.getStringExtra("id");
        rcv = findViewById(R.id.sensorsitem);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        sns = new SensorsAdapter(sensList, switchChangeListener);
        rcv.setAdapter(sns);
        rcv.setHasFixedSize(true);
        fetchSensors(idcuarto);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notif", "ServSecurity", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager.getNotificationChannel("notif") == null) {
                manager.createNotificationChannel(channel);
            }
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "notif")
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
                if (response.isSuccessful() && response.body() != null) {
                    sensList.clear();
                    sensList = response.body();
                    sns.setSensorDataList(sensList);
                    sns.notifyDataSetChanged();

                    for (Sensores sensor:sensList) {
                        if ("sonido".equals(sensor.getFeed_key()) && Float.parseFloat(sensor.getValue()) > 570) {
                            if ("alarma".equals(sensor.getFeed_key()) && Float.parseFloat(sensor.getValue()) != 1) {
                                sendSoundNotification();
                            }
                        }
                        if ("polvo".equals(sensor.getFeed_key()) && Float.parseFloat(sensor.getValue()) > 0.1) {
                            sendDirtNotification();
                        }
                        if ("alarma".equals(sensor.getFeed_key()) && Float.parseFloat(sensor.getValue()) == 1) {
                            sendAlarmNotification();
                        }
                        if ("voltaje".equals(sensor.getFeed_key()) && Float.parseFloat(sensor.getValue()) > 4.5) {
                            sendVoltNotification();
                        }
                    }

                }
            }

            private void sendVoltNotification() {
                Title = "Sobrecarga de Voltaje";
                text = "Hay una sobrecarga de voltaje en tus servidores.";
                if (ContextCompat.checkSelfPermission(SensoresYAdaptadores.this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
                    notificationManagerCompat.notify(notifID++, notification);
                } else {
                    // You might want to request the VIBRATE permission here
                    ActivityCompat.requestPermissions(SensoresYAdaptadores.this, new String[]{Manifest.permission.VIBRATE}, 1823);
                }
            }

            private void sendAlarmNotification() {
                Title = "Humo en Servidores";
                text = "Algo se quemo en los servidores.";
                if (ContextCompat.checkSelfPermission(SensoresYAdaptadores.this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
                    notificationManagerCompat.notify(notifID++, notification);
                } else {
                    // You might want to request the VIBRATE permission here
                    ActivityCompat.requestPermissions(SensoresYAdaptadores.this, new String[]{Manifest.permission.VIBRATE}, 1823);
                }
            }

            private void sendDirtNotification() {
                Title = "Polvo en Servidores";
                text = "Hay falta de mantenimiento en los servidores.";
                if (ContextCompat.checkSelfPermission(SensoresYAdaptadores.this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
                    notificationManagerCompat.notify(notifID++, notification);
                } else {
                    // You might want to request the VIBRATE permission here
                    ActivityCompat.requestPermissions(SensoresYAdaptadores.this, new String[]{Manifest.permission.VIBRATE}, 1823);
                }
            }

            private void sendSoundNotification() {
                Title = "Sonidos en Servidores";
                text = "Parece que hay sonido en los servidores.";
                if (ContextCompat.checkSelfPermission(SensoresYAdaptadores.this, Manifest.permission.VIBRATE) == PackageManager.PERMISSION_GRANTED) {
                    notificationManagerCompat.notify(notifID++, notification);
                } else {
                    // You might want to request the VIBRATE permission here
                    ActivityCompat.requestPermissions(SensoresYAdaptadores.this, new String[]{Manifest.permission.VIBRATE}, 1823);
                }
            }

            @Override
            public void onFailure(Call<List<Sensores>> call, Throwable t) {
                Toast.makeText(SensoresYAdaptadores.this, t.getMessage(),Toast.LENGTH_LONG).show();
                Snackbar snackbar = Snackbar.make(findViewById(R.id.error), t.getMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        });
    }
}