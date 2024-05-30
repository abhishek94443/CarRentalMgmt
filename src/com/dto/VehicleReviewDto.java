package com.dto;

public class VehicleReviewDto {
    private int reviewId;
    private int vehicleId;
    private String vehicle_name;
    private String description;
    private int ratings;

    public VehicleReviewDto(int vehicleId, String vehicle_name, String description, int ratings) {
        super();
        this.vehicleId = vehicleId;
        this.vehicle_name = vehicle_name;
        this.description = description;
        this.ratings = ratings;
    }



    public VehicleReviewDto(int reviewId, int vehicleId, String vehicle_name, String description, int ratings) {
        super();
        this.reviewId = reviewId;
        this.vehicleId = vehicleId;
        this.vehicle_name = vehicle_name;
        this.description = description;
        this.ratings = ratings;
    }

    public VehicleReviewDto() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }




}
