package com.example.retrofitcuartos;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SensorsAdapter  extends RecyclerView.Adapter<SensorsAdapter.SensorsHolder> {
    @NonNull
    @Override
    public SensorsAdapter.SensorsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SensorsAdapter.SensorsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SensorsHolder extends RecyclerView.ViewHolder {
        public SensorsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
