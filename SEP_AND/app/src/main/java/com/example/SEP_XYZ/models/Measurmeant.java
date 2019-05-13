package com.example.SEP_XYZ.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Measurmeant {

    private int CO2;
    private float humidity;
    private float temperature;
    private String dateTime;
    private final DateFormat df = new SimpleDateFormat("d MMM yyyy HH:mm:ss");

    public Measurmeant() {}

    public Measurmeant(int CO2, float humidity, float temperature) {



        this.CO2 = CO2;
        this.humidity = humidity;
        this.temperature = temperature;
        this.dateTime = df.format(Calendar.getInstance().getTime());
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime =df.format(dateTime) ;
    }
}
