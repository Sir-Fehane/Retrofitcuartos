package com.example.retrofitcuartos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

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
    }
}