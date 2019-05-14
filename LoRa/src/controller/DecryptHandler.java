package controller;

import model.Measurements;
import model.Room;

import java.util.ArrayList;
import java.util.Arrays;

public class DecryptHandler {


    private Room room;

    private String extractedData;


    public DecryptHandler() {

        room = new Room();



    }

    public String getExtractedData() {
        System.out.println("getRawData");
        return extractedData;
    }

    public void setExtractedData(String extractedData) {
        System.out.println("rawData");
        this.extractedData = extractedData;
    }


    public void setMeasurementsForRoom() {
        if (getExtractedData() != null) {
            String findBy = "data";
            String data = getExtractedData();
            ArrayList<String> list = new ArrayList<>(Arrays.asList(data.split(",")));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains(findBy)) {
                    String humHex = list.get(i).substring(8, 12);
                    int decHum = Integer.parseInt(humHex, 16);
                    String tempHex = list.get(i).substring(12, 16);
                    int decTemp = Integer.parseInt(tempHex, 16);
                    String co2Hex = list.get(i).substring(16, 20);
                    int decCo2 = Integer.parseInt(co2Hex, 16);
                    double humidity = (double) decHum / 100;
                    double temperature = (double) decTemp / 100;
                    int co2 = decCo2;
                    Measurements measurements = new Measurements(humidity, temperature, co2);
                    room.setMeasurements(measurements);

                }
            }
        }
    }

    public Room getDataForRoom() {

        setMeasurementsForRoom();
        return room;
    }


}
