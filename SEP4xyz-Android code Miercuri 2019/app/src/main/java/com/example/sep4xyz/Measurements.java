package com.example.sep4xyz;

public class Measurements {

    float CO2;
    float humidity;
    float temperature;

    public Measurements(){
        CO2=1500;
        humidity=30;
        temperature=21;
    }

    public Measurements(float CO2, float humidity, float temperature) {
        this.CO2 = CO2;
        this.humidity = humidity;
        this.temperature = temperature;
    }


    public float getCO2() {
        return CO2;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemperature() {
        return temperature;
    }
}
