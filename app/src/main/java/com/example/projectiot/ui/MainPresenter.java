package com.example.projectiot.ui;

import com.example.projectiot.data.repository.MainRepository;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainRepository repository;

    public MainPresenter(MainContract.View view, MainRepository repository) {
        this.view = view;
        this.repository = repository;
    }
}
