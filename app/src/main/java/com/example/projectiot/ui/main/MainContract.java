package com.example.projectiot.ui.main;

import com.example.projectiot.data.model.DataSensor;

public interface MainContract {
    interface View {
        void showMessage(String msg);

        void updateData(DataSensor data);
    }

    interface Presenter {
        void readRealtimeData();
    }
}
