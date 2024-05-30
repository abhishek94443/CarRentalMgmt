package com.model;


public class Vehicle {
    private int id;
    private String vehicle_name;
    private String vehicle_model;
    private String vehicle_year;
    private float daily_rate;
    private int availability_status;
    private int passenger_capacity;
    private String engine_capacity;
    private int vendor_id;

    public Vehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Vehicle(int id, String vehicle_name, String vehicle_model, String vehicle_year, float daily_rate,
                   int availability_status, int passenger_capacity, String engine_capacity, int vendor_id) {
        super();
        this.id = id;
        this.vehicle_name = vehicle_name;
        this.vehicle_model = vehicle_model;
        this.vehicle_year = vehicle_year;
        this.daily_rate = daily_rate;
        this.availability_status = availability_status;
        this.passenger_capacity = passenger_capacity;
        this.engine_capacity = engine_capacity;
        this.vendor_id = vendor_id;
    }


    public Vehicle(String vehicle_name, String vehicle_model, String vehicle_year, float daily_rate,
                   int availability_status, int passenger_capacity, String engine_capacity, int vendor_id) {
        super();
        this.vehicle_name = vehicle_name;
        this.vehicle_model = vehicle_model;
        this.vehicle_year = vehicle_year;
        this.daily_rate = daily_rate;
        this.availability_status = availability_status;
        this.passenger_capacity = passenger_capacity;
        this.engine_capacity = engine_capacity;
        this.vendor_id = vendor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_year() {
        return vehicle_year;
    }

    public void setVehicle_year(String vehicle_year) {
        this.vehicle_year = vehicle_year;
    }

    public float getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(float daily_rate) {
        this.daily_rate = daily_rate;
    }

    public int getAvailability_status() {
        return availability_status;
    }

    public void setAvailability_status(int availability_status) {
        this.availability_status = availability_status;
    }

    public int getPassenger_capacity() {
        return passenger_capacity;
    }

    public void setPassenger_capacity(int passenger_capacity) {
        this.passenger_capacity = passenger_capacity;
    }

    public String getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(String engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", vehicle_name=" + vehicle_name + ", vehicle_model=" + vehicle_model
                + ", vehicle_year=" + vehicle_year + ", daily_rate=" + daily_rate + ", availability_status="
                + availability_status + ", passenger_capacity=" + passenger_capacity + ", engine_capacity="
                + engine_capacity + ", vendor_id=" + vendor_id + "]";
    }



}
