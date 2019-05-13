package com.example.SEP_XYZ.models;

import java.util.ArrayList;

public class RoomList {

    private ArrayList<Room> roomList;

    public RoomList() {

    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    public int getSize()
    {
        return roomList.size();
    }
    public Room getRoomById(String id)
    {
        for (int i=0;i<roomList.size();i++)
        {
            if(roomList.get(i).getRoomId().equals(id))
                return roomList.get(i);

        }
        return null;
    }

    public Room getRoomAtIndex(int index)
    {
        return roomList.get(index);
    }
}
