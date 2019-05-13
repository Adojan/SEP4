package com.example.SEP_XYZ.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.viewmodels.SelectRoomViewModel;

public class SelectRoomActivity extends AppCompatActivity {


    private Spinner blockIdAll;
    private Spinner floorNrAll;
    private Spinner roomNrAll;

    private Spinner blockIdAvailable;
    private Spinner floorNrAvailable;
    private Spinner roomNrAvailable;

    private Button selectRoom;

    private SelectRoomViewModel selectRoomViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        blockIdAll=findViewById(R.id.blockIdAll);
        floorNrAll=findViewById(R.id.floorIdAll);
        roomNrAll=findViewById(R.id.roomNrAll);

        blockIdAvailable=findViewById(R.id.blockIdAvailable);
        floorNrAvailable=findViewById(R.id.floorIdAvailable);
        roomNrAvailable=findViewById(R.id.roomNrAvailable);

        selectRoom=findViewById(R.id.selectRoom);



    }
}
