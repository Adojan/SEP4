package com.example.SEP_XYZ.models;

public class Notification {
    private boolean notifyCO2;
    private boolean notifyTemperature;
    private boolean notifyHumidity;

    private RoomList roomList;

    private final int MIN_CO2 = 350;
    private final int MAX_CO2 = 1000;
    private final float MIN_HUMIDITY = 45;
    private final float MAX_HUMIDITY = 55;
    private final float MIN_TEMPERATURE = 20;
    private final float MAX_TEMPERATURE = 22;

    public Notification() {
        this.notifyCO2 = false;
        this.notifyTemperature = false;
        this.notifyHumidity = false;
    }

    public boolean isNotifyCO2() {
        return notifyCO2;
    }

    public void setNotifyCO2(boolean notifyCO2) {
        this.notifyCO2 = notifyCO2;
    }

    public boolean isNotifyTemperature() {
        return notifyTemperature;
    }

    public void setNotifyTemperature(boolean notifyTemperature) {
        this.notifyTemperature = notifyTemperature;
    }

    public boolean isNotifyHumidity() {
        return notifyHumidity;
    }

    public void setNotifyHumidity(boolean notifyHumidity) {
        this.notifyHumidity = notifyHumidity;
    }

    public RoomList getRoomList() {
        return roomList;
    }

    public void setRoomList(RoomList roomList) {
        this.roomList = roomList;
    }


    public void checkParameters(RoomList roomList) {
        for (int i = 0; i < roomList.getSize(); i++) {

            if (getMeasurmentFromRoomList(i).getCO2() > MAX_CO2 || getMeasurmentFromRoomList(i).getCO2() < MIN_CO2) {
                setNotifyCO2(true);
            }
            if (getMeasurmentFromRoomList(i).getCO2() < MAX_CO2 && getMeasurmentFromRoomList(i).getCO2() > MIN_CO2) {
                setNotifyCO2(false);
            }

            if (getMeasurmentFromRoomList(i).getHumidity() > MAX_HUMIDITY || getMeasurmentFromRoomList(i).getHumidity() < MIN_HUMIDITY) {
                setNotifyCO2(true);
            }
            if (getMeasurmentFromRoomList(i).getHumidity() < MAX_HUMIDITY && getMeasurmentFromRoomList(i).getHumidity() > MIN_HUMIDITY) {
                setNotifyCO2(false);
            }

            if (getMeasurmentFromRoomList(i).getTemperature() > MAX_TEMPERATURE || getMeasurmentFromRoomList(i).getTemperature() < MIN_TEMPERATURE) {
                setNotifyCO2(true);
            }
            if (getMeasurmentFromRoomList(i).getTemperature() < MAX_TEMPERATURE && getMeasurmentFromRoomList(i).getTemperature() > MIN_TEMPERATURE) {
                setNotifyCO2(false);
            }

        }
    }

    public Measurmeant getMeasurmentFromRoomList(int i) {
        return roomList.getRoomAtIndex(i).getMeasurmeant();
    }

}
