package com.minhal.fyp.Model;

public class SensorData {
    private Double Temperature;
    private Double Humidity;
    public SensorData(){}
    public SensorData(Double Humidity, Double Temperature) {
        this.Temperature=Temperature;
        this.Humidity=Humidity;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public Double getTemperature() {
        return Temperature;
    }
}
