package com.example.projectiot.ui.login;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.example.projectiot.data.repository.login.LoginRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LoginRepository repository;

    public LoginPresenter(LoginContract.View view, LoginRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void handlerLogin(String email, String password) {
        repository.login(email, password, new OnDataLoadedListener<String>() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onFailure(Exception exception) {

            }
        });
    }
}
