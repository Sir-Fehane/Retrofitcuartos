package com.example.retrofitcuartos.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.retrofitcuartos.models.SensorList;
import com.example.retrofitcuartos.models.Sensores;
import com.example.retrofitcuartos.repository.SensorsRepository;

import java.util.List;

public class SensorsViewModel extends ViewModel {
    private MutableLiveData<List<Sensores>> sensores;
    private SensorsRepository sensorsRepository;
    public LiveData<List<Sensores>> getSensores (String id) {
        if (sensores == null) {
            sensores = new MutableLiveData<>();
            loadSensors(id);
        }
        return sensores;
    }

    private void loadSensors (String id) {
        if (sensorsRepository == null) {
            sensorsRepository = new SensorsRepository();
        }
        LiveData<List<Sensores>> repositoryLiveData = sensorsRepository.getSensors(id);
        repositoryLiveData.observeForever(new Observer<List<Sensores>>() {
            @Override
            public void onChanged(List<Sensores> sensList) {
                sensores.setValue(sensList);
            }
        });
    }
}
