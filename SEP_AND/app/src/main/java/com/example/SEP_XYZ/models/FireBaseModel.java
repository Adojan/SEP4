package com.example.SEP_XYZ.models;

import com.google.firebase.auth.FirebaseAuth;

public class FireBaseModel {
    private FirebaseAuth mFIreBaseAuth;
    public FireBaseModel()
    {

    }

    public FirebaseAuth getmFIreBaseAuth() {
        return mFIreBaseAuth.getInstance();
    }
}
