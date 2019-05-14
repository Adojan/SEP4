package model;

public class Measurements {
    private double temperature;
    private double humidity;
    private int co2;
    private DateTime dateTime;

    public Measurements(double humidity, double temperature, int co2) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        //   dateTime.getDate();
    }

    public Measurements() {
        temperature = 20;
        humidity = 30;
        co2 = 100;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                '}';
    }
}
