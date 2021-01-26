package com.example.projectiot.data.model;

public class DataModel {
    private float co;
    private float lpg;
    private float smoke;
    private float humidity;
    private float temperature;

    public DataModel(float co, float lpg, float smoke, float humidity, float temperature) {
        this.co = co;
        this.lpg = lpg;
        this.smoke = smoke;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public float getCo() {
        return co;
    }

    public float getLpg() {
        return lpg;
    }

    public float getSmoke() {
        return smoke;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }
}
