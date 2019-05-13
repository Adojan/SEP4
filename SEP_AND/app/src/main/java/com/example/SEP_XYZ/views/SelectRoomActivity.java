package com.example.SEP_XYZ.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

        String[] allBlockIds = getResources().getStringArray(R.array.blockIds);
        String[] allFloorIds = getResources().getStringArray(R.array.floorNrs);
        String[] allRoomIds = getResources().getStringArray(R.array.roomNrs);

        blockIdAll=findViewById(R.id.blockIdAll);
        floorNrAll=findViewById(R.id.floorIdAll);
        roomNrAll=findViewById(R.id.roomNrAll);

        blockIdAvailable=findViewById(R.id.blockIdAvailable);
        floorNrAvailable=findViewById(R.id.floorIdAvailable);
        roomNrAvailable=findViewById(R.id.roomNrAvailable);

        selectRoom=findViewById(R.id.selectRoom);

        selectRoomViewModel = new SelectRoomViewModel();

        selectRoomViewModel.setAdapter(this,allBlockIds,blockIdAll);
        selectRoomViewModel.setAdapter(this,allFloorIds,floorNrAll);
        selectRoomViewModel.setAdapter(this,allRoomIds,roomNrAll);
//        selectRoomViewModel.setAdapter(this,allBlockIds,"1");
//        selectRoomViewModel.setAdapter(this,allBlockIds,blockIdAll);
//        selectRoomViewModel.setAdapter(this,allBlockIds,blockIdAll);


    }
//    public void setAdapter(String[] ids, Spinner spinner)
//    {
//        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,ids);
//        spinner.setAdapter(arrayAdapter);
//    }


}
