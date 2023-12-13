package com.example.retrofitcuartos.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitcuartos.R;
import com.example.retrofitcuartos.models.Sensores;

import java.util.List;

public class SensorsAdapter  extends RecyclerView.Adapter<SensorsAdapter.SensorsHolder> {
    private List<Sensores> listsen;

    public SensorsAdapter(List<Sensores> listsen) {
        this.listsen = listsen;
    }

    @NonNull
    @Override
    public SensorsAdapter.SensorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lyf = LayoutInflater.from(parent.getContext());
        View v = lyf.inflate(R.layout.activity_sensor_items,parent,false);
        return new SensorsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorsAdapter.SensorsHolder holder, int position) {
        Sensores sen = listsen.get(position);
        holder.setData(sen);
    }

    @Override
    public int getItemCount() {
        return listsen.size();
    }

    public class SensorsHolder extends RecyclerView.ViewHolder {
        TextView nam;
        TextView dat;
        Switch sw;
        public SensorsHolder(@NonNull View itemView) {
            super(itemView);
            nam = itemView.findViewById(R.id.sensor);
            dat = itemView.findViewById(R.id.data);
            sw = itemView.findViewById(R.id.actor);
        }

        public void setData(Sensores sen) {
            nam.setText(sen.getFeed_key());
            dat.setText(String.valueOf(sen.getValue()));
            if("Temperatura".equals(sen.getFeed_key())){
                sw.setText("Ventilacion");
            }
            else if("NFC".equals(sen.getFeed_key())){
                sw.setText("Puerta");
            }
            else if("Humo".equals(sen.getFeed_key())){
                sw.setText("Alarma");
            }
            else{
                sw.setVisibility(View.GONE);
            }
        }
    }
}
