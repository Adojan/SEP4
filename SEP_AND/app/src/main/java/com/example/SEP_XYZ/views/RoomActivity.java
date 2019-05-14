package com.example.SEP_XYZ.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.SEP_XYZ.R;

public class RoomActivity extends AppCompatActivity {

    private TextView roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        roomId=findViewById(R.id.roomIdIntent);
        roomId.setText(getIntent().getStringExtra("id"));
    }


}
