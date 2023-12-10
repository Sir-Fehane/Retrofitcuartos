package com.example.retrofitcuartos;

public class Cuartos {
    private String nombre;
    private String propietario;

    public Cuartos(String nombre, String propietario) {
        this.nombre = nombre;
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
}
