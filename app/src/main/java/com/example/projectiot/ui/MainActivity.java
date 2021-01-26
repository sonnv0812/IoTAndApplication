package com.example.projectiot.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectiot.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
