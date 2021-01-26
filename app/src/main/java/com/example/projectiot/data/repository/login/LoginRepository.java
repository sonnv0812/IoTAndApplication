package com.example.projectiot.data.repository.login;

import com.example.projectiot.data.base.OnDataLoadedListener;

public interface LoginRepository {
    void login(String email, String password, OnDataLoadedListener<String> callback);
}
