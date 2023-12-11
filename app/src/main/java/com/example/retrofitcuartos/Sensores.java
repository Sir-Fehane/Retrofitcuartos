package com.example.retrofitcuartos;

public class Sensores {
    String feed_key;
    double value;

    public Sensores(String feed_key, double value) {
        this.feed_key = feed_key;
        this.value = value;
    }

    public String getFeed_key() {
        return feed_key;
    }

    public void setFeed_key(String feed_key) {
        this.feed_key = feed_key;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
