package com.example.retrofitcuartos.models;

public class Cuartos {
    private int id;
    private String nombre;
    private String propietario;

    public Cuartos(int id, String nombre, String propietario) {
        this.nombre = nombre;
        this.propietario = propietario;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
