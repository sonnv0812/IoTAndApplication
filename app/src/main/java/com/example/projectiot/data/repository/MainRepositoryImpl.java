package com.example.projectiot.data.repository;

import androidx.annotation.NonNull;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.example.projectiot.data.model.DataModel;
import com.example.projectiot.data.model.TimestampModel;
import com.example.projectiot.data.repository.MainRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainRepositoryImpl implements MainRepository {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    public void loadData(OnDataLoadedListener<DataModel> callbackData, OnDataLoadedListener<TimestampModel> callbackTime) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
