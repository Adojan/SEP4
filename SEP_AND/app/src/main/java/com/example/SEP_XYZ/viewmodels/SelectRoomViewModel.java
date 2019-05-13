package com.example.SEP_XYZ.viewmodels;

import com.example.SEP_XYZ.models.RoomList;

public class SelectRoomViewModel {

    private RoomList roomList;

    public void init()
    {
        if (roomList != null) return;
        roomList = new RoomList();
    }
}
