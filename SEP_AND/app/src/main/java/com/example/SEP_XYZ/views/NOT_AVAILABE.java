package com.example.SEP_XYZ.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.SEP_XYZ.R;

public class NOT_AVAILABE extends AppCompatActivity {
    private TextView roomId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not__availabe);
        roomId = findViewById(R.id.textView10);
        roomId.setText(getIntent().getStringExtra("id"));
    }
}
