package com.model;

public class CustomerHistory {
    private int id;
    private double discount;
    private double late_return_fee;
    private int start_mileage;
    private int end_mileage;
    private String any_damage_reported;
    private int customer_id;
    private int vehicle_id;
    private double finalAmount;

    public CustomerHistory() {
        super();
        // TODO Auto-generated constructor stub
    }
    public CustomerHistory(int id, double discount, double late_return_fee, int start_mileage, int end_mileage,
                           String any_damage_reported, int customer_id, int vehicle_id) {
        super();
        this.id = id;
        this.discount = discount;
        this.late_return_fee = late_return_fee;
        this.start_mileage = start_mileage;
        this.end_mileage = end_mileage;
        this.any_damage_reported = any_damage_reported;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
    }


    public CustomerHistory(double discount, double late_return_fee, int start_mileage, int end_mileage,
                           String any_damage_reported, int customer_id, int vehicle_id, double finalAmount) {
        super();
        this.discount = discount;
        this.late_return_fee = late_return_fee;
        this.start_mileage = start_mileage;
        this.end_mileage = end_mileage;
        this.any_damage_reported = any_damage_reported;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.finalAmount = finalAmount;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getLate_return_fee() {
        return late_return_fee;
    }
    public void setLate_return_fee(double late_return_fee) {
        this.late_return_fee = late_return_fee;
    }
    public int getStart_mileage() {
        return start_mileage;
    }
    public void setStart_mileage(int start_mileage) {
        this.start_mileage = start_mileage;
    }
    public int getEnd_mileage() {
        return end_mileage;
    }
    public void setEnd_mileage(int end_mileage) {
        this.end_mileage = end_mileage;
    }
    public String getAny_damage_reported() {
        return any_damage_reported;
    }
    public void setAny_damage_reported(String any_damage_reported) {
        this.any_damage_reported = any_damage_reported;
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
    public double getFinalAmount() {
        return finalAmount;
    }
    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }
    @Override
    public String toString() {
        return "CustomerHistory [id=" + id + ", discount=" + discount + ", late_return_fee=" + late_return_fee
                + ", start_mileage=" + start_mileage + ", end_mileage=" + end_mileage + ", any_damage_reported="
                + any_damage_reported + ", customer_id=" + customer_id + ", vehicle_id=" + vehicle_id + "]";
    }
}
