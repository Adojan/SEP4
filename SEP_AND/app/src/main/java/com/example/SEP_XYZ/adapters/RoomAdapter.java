package com.example.SEP_XYZ.adapters;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.SEP_XYZ.R;
import com.example.SEP_XYZ.models.Room;
import com.example.SEP_XYZ.views.SelectRoomActivity;

import java.util.List;
import java.util.ResourceBundle;

public class RoomAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Room> roomList;




    public RoomAdapter(List<Room> roomList)
    {
        this.roomList=roomList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v =(View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
         final int[] icons = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g};

        Room room=roomList.get(i);
            viewHolder.roomNr.setText(room.getRoomNr());
            viewHolder.floorId.setText((room.getFloorNr()));
            viewHolder.roomId.setText(room.getRoomId());







    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }
    public void setIcon(String s)
    {
        switch ( s)
        {
            case "A":


        }
    }



}
