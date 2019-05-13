package com.example.SEP_XYZ.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.models.RoomList;
import com.example.SEP_XYZ.repositories.RoomsRepository;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomViewModel {

    private RoomList roomList;
    private MutableLiveData<List<Room>> mtbRoom;
    private RoomsRepository roomsRepository;

    public void init()
    {
        if (mtbRoom != null) return;
        roomsRepository=RoomsRepository.getInstance();
        mtbRoom=roomsRepository.getRooms();


    }
    public LiveData<List<Room>> getRooms()
    {
        return mtbRoom;
    }

    public void setOnBlockIdAll(Spinner spinner)
    {

    }


}
