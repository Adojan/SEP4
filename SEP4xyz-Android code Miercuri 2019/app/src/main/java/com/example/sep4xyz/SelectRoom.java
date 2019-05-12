package com.example.sep4xyz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectRoom extends AppCompatActivity {

    TextView F307;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);
        F307=findViewById(R.id.F307);
    }
    public void openroom (View view){
        Intent openRoom= new Intent(SelectRoom.this, ViewMeasurements.class);
        startActivity(openRoom);
    }
}
