package com.example.SEP_XYZ.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.adapters.RoomAdapter;
import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.models.RoomList;
import com.example.SEP_XYZ.repositories.RoomsRepository;
import com.example.SEP_XYZ.views.SelectRoomActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomViewModel {


    private MutableLiveData<List<Room>> mtbRoom;
    private RoomsRepository roomsRepository;


    public void init() {
        if (mtbRoom != null) return;
        roomsRepository = RoomsRepository.getInstance();
        mtbRoom = roomsRepository.getRooms();
    }


    public LiveData<List<Room>> getRooms() {
        return mtbRoom;
    }

    public void setAdapter(Context context, String[] ids, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, ids);
        spinner.setAdapter(arrayAdapter);
    }

    public void setAvailableRoomInfo(Room room, TextView flnr, TextView rmnr, TextView rmid) {
        flnr.setText("Floor number: " + room.getFloorNr());
        rmnr.setText("Room number: " + room.getRoomNr());
        rmid.setText("Room id: " + room.getRoomId());

    }

    public RoomsRepository getRoomsRepository() {
        return roomsRepository;
    }

    public boolean verifyAvailability(Spinner block, Spinner floor, Spinner roomNr, Room room) {
        Room tmp = new Room(block.getSelectedItem().toString(), floor.getSelectedItem().toString(), roomNr.getSelectedItem().toString());
        if (room.getRoomId().equals(tmp.getRoomId())) {
            return true;
        }
        return false;
    }

    public String createRoomReturnID(Spinner block, Spinner floor, Spinner roomNr) {
        Room tmp = new Room(block.getSelectedItem().toString(), floor.getSelectedItem().toString(), roomNr.getSelectedItem().toString());
        return tmp.getRoomId();
    }


}
