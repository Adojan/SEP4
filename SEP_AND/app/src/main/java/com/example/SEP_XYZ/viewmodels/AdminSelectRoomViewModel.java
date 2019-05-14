package com.example.SEP_XYZ.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.adapters.RoomAdapter;
import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.repositories.RoomsRepository;

import java.util.List;

public class AdminSelectRoomViewModel {
    private MutableLiveData<List<Room>> mtbRoom;
    private RoomsRepository roomsRepository;
    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private final int[] icons = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};


    private ArrayAdapter arrayAdapter;


    public void init()
    {
        if (mtbRoom != null) return;
        roomsRepository=RoomsRepository.getInstance();
        mtbRoom=roomsRepository.getRooms();
    }
    public void initRecyclerView(List<Room> roomList, Context context)
    {
        roomAdapter = new RoomAdapter(roomList);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(roomAdapter);
    }

    public LiveData<List<Room>> getRooms()
    {
        return mtbRoom;
    }

    public void setAdapter(Context context,String[] ids, Spinner spinner)
    {
        ArrayAdapter arrayAdapter = new ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,ids);
        spinner.setAdapter(arrayAdapter);
    }
    public void setAvailableRoomInfo(Room room, TextView flnr, TextView rmnr, TextView rmid )
    {
        flnr.setText("Floor number: "+room.getFloorNr());
        rmnr.setText("Room number: "+room.getRoomNr());
        rmid.setText("Room id: "+room.getRoomId());

    }
    public RoomsRepository getRoomsRepository()
    {
        return roomsRepository;
    }



}
