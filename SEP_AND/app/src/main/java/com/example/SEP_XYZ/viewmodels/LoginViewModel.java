package com.example.SEP_XYZ.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.SEP_XYZ.models.Administrator;
import com.example.SEP_XYZ.models.FireBaseModel;


public class LoginViewModel extends ViewModel {

    private FireBaseModel mFireBaseModel;
    private Administrator administrator;

    public void init() {
        if (mFireBaseModel != null) return;
        mFireBaseModel = new FireBaseModel();
        if (administrator != null) return;
        administrator = new Administrator();
    }

    public FireBaseModel getmFireBaseModel() {
        return mFireBaseModel;
    }

    public String geStringFromTextView(TextView textView) {
        String s = textView.getText().toString();
        return s;
    }

    public boolean checkEmptyTextField(TextView textView) {
        return TextUtils.isEmpty(textView.getText().toString());
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public boolean checkIfAdministrator(TextView email, TextView password) {
        String emailAdmin = geStringFromTextView(email);
        String passwordAdmin = geStringFromTextView(password);
        return (administrator.getEmail().equals(emailAdmin) && administrator.getPassword().equals(passwordAdmin));

    }


}
