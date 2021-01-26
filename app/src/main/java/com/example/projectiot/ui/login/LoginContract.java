package com.example.projectiot.ui.login;

public interface LoginContract {
    interface View {
        void nextActivity();
    }

    interface Presenter {
        void handlerLogin(String email, String password);
    }
}
