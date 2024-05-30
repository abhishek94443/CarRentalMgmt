package com.dto;

// This model is used for vendors to see reviews on their vehicles by customers
public class VehicleReviewCustomerDto {
    private String vehicleName;
    private String customer;
    private String description;
    private int ratings;

    public VehicleReviewCustomerDto(String vehicleName, String customer, String description, int ratings) {
        super();
        this.vehicleName = vehicleName;
        this.customer = customer;
        this.description = description;
        this.ratings = ratings;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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
