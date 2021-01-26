package com.example.projectiot.data.model;

public class TimestampModel {
    private int day;
    private int hour;
    private int min;
    private int month;
    private int sec;
    private int year;

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
}
