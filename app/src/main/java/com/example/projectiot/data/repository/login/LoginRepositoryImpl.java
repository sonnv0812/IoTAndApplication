package com.example.projectiot.data.repository.login;

import com.example.projectiot.data.base.OnDataLoadedListener;
import com.firebase.ui.auth.AuthUI;

public class LoginRepositoryImpl implements LoginRepository {

    private static final int RC_SIGN_IN = 100;
    private static final String GOOGLE_TOS_URL = "https://www.google.com/policies/terms/";
    private static final String FIREBASE_TOS_URL = "https://firebase.google.com/terms/";
    private static final String GOOGLE_PRIVACY_POLICY_URL = "https://www.google" +
            ".com/policies/privacy/";
    private static final String FIREBASE_PRIVACY_POLICY_URL = "https://firebase.google" +
            ".com/terms/analytics/#7_privacy";

    @Override
    public void login(String email, String password, OnDataLoadedListener<String> callback) {
        AuthUI.IdpConfig provider = new AuthUI.IdpConfig.EmailBuilder().build();

    }


}
