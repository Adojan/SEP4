package com.example.SEP_XYZ.views;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.adapters.ViewHolder;
import com.example.SEP_XYZ.viewmodels.SelectRoomViewModel;

public class SelectRoomActivity extends AppCompatActivity {


    private Spinner blockIdAll;
    private Spinner floorNrAll;
    private Spinner roomNrAll;

    private TextView roomId;
    private TextView floorNrAvailable;
    private TextView roomNrAvailable;

    private Button selectRoom;

    private ImageView blockIdImage;

    private LinearLayout availableRoomLayout;



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

        roomId=findViewById(R.id.roomId);
        floorNrAvailable=findViewById(R.id.floorNr);
        roomNrAvailable=findViewById(R.id.roomNr);

        selectRoom=findViewById(R.id.selectRoom);

        blockIdImage=findViewById(R.id.blockImg);

        availableRoomLayout=findViewById(R.id.availableROmmLayout);

        selectRoomViewModel = new SelectRoomViewModel();
        selectRoomViewModel.init();

        selectRoomViewModel.setAdapter(this,allBlockIds,blockIdAll);
        selectRoomViewModel.setAdapter(this,allFloorIds,floorNrAll);
        selectRoomViewModel.setAdapter(this,allRoomIds,roomNrAll);

    selectRoomViewModel.setAvailableRoomInfo(selectRoomViewModel.getRoomsRepository().getAvailableRoom(),floorNrAvailable,roomNrAvailable,roomId);

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
