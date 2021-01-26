package com.example.projectiot.data.repository.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.example.projectiot.data.model.DataSensor;
import com.example.projectiot.data.model.TimestampModel;
import com.example.projectiot.data.repository.main.MainRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainRepositoryImpl implements MainRepository {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String DEVICE = "Device1";
    private static final String DATA = "Data";
    private static final String TIME_STAMP = "Timestamp";

    @Override
    public void loadData(OnDataLoadedListener<DataSensor> callback) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(DEVICE);
        final DataSensor[] data = new DataSensor[1];
        final TimestampModel[] time = new TimestampModel[1];

        myRef.child(DATA).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data[0] = snapshot.getValue(DataSensor.class);
                Log.v("TEST", data[0].toString());
                callback.onSuccess(data[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child(TIME_STAMP).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                time[0] = snapshot.getValue(TimestampModel.class);
                Log.v("TEST", time[0].toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
