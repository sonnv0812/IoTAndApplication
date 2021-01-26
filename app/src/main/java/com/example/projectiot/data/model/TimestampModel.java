package com.example.projectiot.data.model;

public class TimestampModel {
    private int day;
    private int hour;
    private int min;
    private int month;
    private int sec;
    private int year;

    public TimestampModel() {
        this.day = 0;
        this.hour = 0;
        this.min = 0;
        this.month = 0;
        this.sec = 0;
        this.year = 0;
    }

    public TimestampModel(int day, int hour, int min, int month, int sec, int year) {
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.month = month;
        this.sec = sec;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getMonth() {
        return month;
    }

    public int getSec() {
        return sec;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "TimestampModel{" +
                "day=" + day +
                ", hour=" + hour +
                ", min=" + min +
                ", month=" + month +
                ", sec=" + sec +
                ", year=" + year +
                '}';
    }
}
