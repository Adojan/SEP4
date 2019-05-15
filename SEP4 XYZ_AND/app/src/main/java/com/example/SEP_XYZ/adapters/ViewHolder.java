package com.example.SEP_XYZ.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.SEP_XYZ.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public final View view;
    public final TextView roomId;
    public final TextView floorId;
    public final TextView roomNr;
    public final ImageView blockImg;

    public ViewHolder(View view)
    {
        super(view);
        this.view=view;
        roomId=view.findViewById(R.id.roomIdIntent);
        floorId=view.findViewById(R.id.roomNr);
        roomNr=view.findViewById(R.id.rommNr);
        blockImg=view.findViewById(R.id.blockImg);


    }
}
