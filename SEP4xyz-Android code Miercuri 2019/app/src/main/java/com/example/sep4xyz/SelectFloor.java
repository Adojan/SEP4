package com.example.sep4xyz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectFloor extends AppCompatActivity {

    TextView floor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_floor);
        floor3=findViewById(R.id.Floor3);
    }
    public void selectThirdFloor (View view){
        Intent selectFloor= new Intent(this,SelectRoom.class);
        startActivity(selectFloor);
    }
}
