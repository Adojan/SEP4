package com.example.SEP_XYZ.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.SEP_XYZ.models.FireBaseModel;

public class CreateTechnicianViewModel extends ViewModel {
    private FireBaseModel mFireBaseModel;

    public void init() {
        if (mFireBaseModel != null) return;
        mFireBaseModel = new FireBaseModel();
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

    public boolean checkLenght(String s) {
        if (s.length() < 6)
            return true;
        return false;
    }


}
