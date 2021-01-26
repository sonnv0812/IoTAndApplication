package com.example.projectiot.data.model;

public class DataPoint {
    private int xValue;
    private int yValue;

    public DataPoint() {

    }

    public DataPoint(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }
}
