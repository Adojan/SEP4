package com.example.SEP_XYZ.repositories;

import android.arch.lifecycle.MutableLiveData;




import com.example.SEP_XYZ.models.Measurmeant;
import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.models.RoomList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class RoomsRepository {
    private String[] blockId = {"A", "B", "C", "D", "E", "F", "G"};
    private String[] floorNr = {"0", "1", "2", "3"};
    private String[] roomNr = {"01", "02", "03", "04", "05", "06", "07"};




    int i, j, k;

    private ArrayList<Room> rooms = new ArrayList<>();
    private RoomList availableRooms = new RoomList();
    private Room availableRoom = new Room("F", "3", "07");
    private Measurmeant measurmeant = new Measurmeant(400, 50, 21);

    private static RoomsRepository instance;


    public static RoomsRepository getInstance() {
        if (instance == null)
            instance = new RoomsRepository();
        return instance;

    }

    //Pretend to get data from a webservice
    public MutableLiveData<List<Room>> getRooms() {
        setRooms();
        MutableLiveData<List<Room>> mtbRooms = new MutableLiveData<>();
        mtbRooms.setValue(rooms);
        return mtbRooms;
    }



    public void setRooms() {
        for (i = 0; i < blockId.length; i++) {
            for (k = 0; k < floorNr.length; k++) {
                for (j = 0; j < roomNr.length; j++) {
                    rooms.add(new Room(blockId[i], floorNr[k], roomNr[j]));
                }
            }
        }
        availableRoom.setMeasurmeant(measurmeant);
        availableRooms.addRoom(availableRoom);
    }

    public ArrayList<Room> getRoomsArayList() {
        return rooms;
    }

    public int size() {
        return rooms.size();
    }

    public RoomList getAvailableRooms() {
        return availableRooms;
    }

    public Room getAvailableRoom() {
        return availableRoom;
    }


}
