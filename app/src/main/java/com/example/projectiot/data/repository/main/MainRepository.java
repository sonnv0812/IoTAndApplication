package com.example.projectiot.data.repository.main;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.example.projectiot.data.model.DataSensor;

public interface MainRepository {
    void loadData(OnDataLoadedListener<DataSensor> callback);
}
