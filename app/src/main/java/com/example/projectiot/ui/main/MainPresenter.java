package com.example.projectiot.ui.main;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.example.projectiot.data.model.DataSensor;
import com.example.projectiot.data.repository.main.MainRepository;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainRepository repository;

    public MainPresenter(MainContract.View view, MainRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void readRealtimeData() {
        repository.loadData(new OnDataLoadedListener<DataSensor>() {
            @Override
            public void onSuccess(DataSensor data) {
                view.updateData(data);
            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
