package model;

public class Room {
    private Measurements measurements;

    public Room() {
        measurements = new Measurements();
    }

    public Room(Measurements measurements) {
        this.measurements = measurements;
    }


    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "Room{" +
                "measurements=" + measurements.toString() +
                '}';
    }
}
