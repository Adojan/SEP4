package com.example.SEP_XYZ.views;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.repositories.RoomsRepository;
import com.example.SEP_XYZ.viewmodels.RoomActivityViewModel;

public class RoomActivity extends AppCompatActivity {

    private TextView roomId;

    private TextView Co2;
    private TextView temperature;
    private TextView humidity;

    private RoomsRepository roomsRepository;
    private RoomActivityViewModel roomActivityViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        roomId = findViewById(R.id.roomIdIntent);
        roomId.setText(getIntent().getStringExtra("id"));

        roomActivityViewModel = new RoomActivityViewModel();
        roomActivityViewModel.init();

        roomsRepository = RoomsRepository.getInstance();
        roomsRepository.setRooms();

        Co2 = findViewById(R.id.CO2);
        temperature = findViewById(R.id.Temperature);
        humidity = findViewById(R.id.Humidity);


        roomActivityViewModel.setData(getIntent().getStringExtra("id"), Co2, temperature, humidity);
    }


}
