package com.example.SEP_XYZ.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.repositories.RoomsRepository;
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

    private Room available;
    private RoomsRepository roomsRepository;


    private SelectRoomViewModel selectRoomViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        String[] allBlockIds = getResources().getStringArray(R.array.blockIds);
        String[] allFloorIds = getResources().getStringArray(R.array.floorNrs);
        String[] allRoomIds = getResources().getStringArray(R.array.roomNrs);

        blockIdAll = findViewById(R.id.blockIdAll);
        floorNrAll = findViewById(R.id.floorIdAll);
        roomNrAll = findViewById(R.id.roomNrAll);

        roomId = findViewById(R.id.roomIdIntent);
        floorNrAvailable = findViewById(R.id.floorNr);
        roomNrAvailable = findViewById(R.id.roomNr);

        selectRoom = findViewById(R.id.selectRoom);

        blockIdImage = findViewById(R.id.blockImg);

        availableRoomLayout = findViewById(R.id.availableROmmLayout);

        selectRoomViewModel = new SelectRoomViewModel();
        selectRoomViewModel.init();

        selectRoomViewModel.setAdapter(this, allBlockIds, blockIdAll);
        selectRoomViewModel.setAdapter(this, allFloorIds, floorNrAll);
        selectRoomViewModel.setAdapter(this, allRoomIds, roomNrAll);

        roomsRepository = RoomsRepository.getInstance();
        roomsRepository.setRooms();

        selectRoomViewModel.setAvailableRoomInfo(selectRoomViewModel.getRoomsRepository().getAvailableRoom(), floorNrAvailable, roomNrAvailable, roomId);

        available = roomsRepository.getAvailableRoom();

        selectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectRoomViewModel.verifyAvailability(blockIdAll, floorNrAll, roomNrAll, available)) {
                    goToRoom(v);
                    return;
                }
                goToNotAvailabe(v);
            }
        });


    }

    public void goToRoom(View view) {
        Intent intent = new Intent(SelectRoomActivity.this, RoomActivity.class);
        intent.putExtra("id", available.getRoomId());
        startActivity(intent);
    }

    public void goToNotAvailabe(View view) {


        Intent intent = new Intent(SelectRoomActivity.this, NOT_AVAILABE.class);
        intent.putExtra("id", selectRoomViewModel.createRoomReturnID(blockIdAll, floorNrAll, roomNrAll));
        startActivity(intent);
    }


}
