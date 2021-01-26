package com.example.projectiot.data.repository;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.example.projectiot.data.model.DataModel;
import com.example.projectiot.data.model.TimestampModel;

public interface MainRepository {
    void loadData(OnDataLoadedListener<DataModel> callbackData, OnDataLoadedListener<TimestampModel> callbackTime);
}
