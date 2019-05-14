package com.example.SEP_XYZ.models;


import java.io.Serializable;

public class Room implements Serializable {
    private String blockId;
    private String floorNr;
    private String roomNr;

    private Measurmeant measurmeant;

    //roomId is generated from the previous 3 Strings
    private String roomId;

    public Room(String blockId, String floorNr, String roomNr) {
        this.blockId = blockId;
        this.floorNr = floorNr;
        this.roomNr = roomNr;

        generateId();
    }

    public Room( ) {
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getFloorNr() {
        return floorNr;
    }

    public void setFloorNr(String floorNr) {
        this.floorNr = floorNr;
    }

    public String getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(String roomNr) {
        this.roomNr = roomNr;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public void generateId()
    {
        this.roomId=(""+this.blockId.toString()+this.floorNr.toString()+this.roomNr.toString()).trim();
    }

    public Measurmeant getMeasurmeant() {
        return measurmeant;
    }

    public void setMeasurmeant(Measurmeant measurmeant) {
        this.measurmeant = measurmeant;
    }
}

