package com.dto;

public class ReviewDto {
    String vehicle_name;
    double average;

    public ReviewDto(String vehicle_name, double average) {
        super();
        this.vehicle_name = vehicle_name;
        this.average = average;
    }

    public ReviewDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

}
