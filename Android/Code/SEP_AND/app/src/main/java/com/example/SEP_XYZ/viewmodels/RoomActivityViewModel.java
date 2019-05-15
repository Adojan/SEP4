package com.example.SEP_XYZ.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.TextView;

import com.example.SEP_XYZ.adapters.RoomAdapter;
import com.example.SEP_XYZ.models.Measurmeant;
import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.repositories.RoomsRepository;

import java.util.List;


public class RoomActivityViewModel extends ViewModel {
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

    public void setData(String id, TextView CO2, TextView temperature, TextView humidity) {
        Room room = roomsRepository.getAvailableRooms().getRoomById(id);
        Measurmeant temp = room.getMeasurmeant();
        CO2.setText(String.valueOf(temp.getCO2()));
        temperature.setText(String.valueOf(temp.getTemperature()));
        humidity.setText(String.valueOf(temp.getHumidity()));
    }
}
