package com.model;


public class Lease {
    private int customer_id;
    private int vehicle_id;
    private int id;
    private String start_date;
    private String end_date;
    private String status;
    private String type;

    public Lease() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Lease(int customer_id, int vehicle_id, String start_date, String end_date, String status, String type) {
        super();
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.type = type;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lease [customer_id=" + customer_id + ", vehicle_id=" + vehicle_id + ", id=" + id + ", start_date="
                + start_date + ", end_date=" + end_date + ", status=" + status + ", type=" + type + "]";
    }



}
