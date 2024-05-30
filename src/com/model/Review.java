package com.model;

public class Review {
    private int id;
    private String description;
    private int ratings;
    private int customer_id;
    private int vehicle_id;

    public Review() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Review(int id, String description, int ratings, int customer_id, int vehicle_id) {
        super();
        this.id = id;
        this.description = description;
        this.ratings = ratings;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

}
