package com.example.projectiot.data.model;

public class DataSensor {
    private float CO;
    private float LPG;
    private float Smoke;
    private float humidity;
    private float temperature;

    public DataSensor() {
        this.CO = 0;
        this.LPG = 0;
        this.Smoke = 0;
        this.humidity = 0;
        this.temperature = 0;
    }

    public DataSensor(float co, float lpg, float smoke, float humidity, float temperature) {
        this.CO = co;
        this.LPG = lpg;
        this.Smoke = smoke;
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public float getCO() {
        return CO;
    }

    public float getLPG() {
        return LPG;
    }

    public float getSmoke() {
        return Smoke;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "DataSensor{" +
                "co=" + CO +
                ", lpg=" + LPG +
                ", smoke=" + Smoke +
                ", humidity=" + humidity +
                ", temperature=" + temperature +
                '}';
    }
}
