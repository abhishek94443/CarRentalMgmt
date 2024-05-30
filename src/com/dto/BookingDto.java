package com.dto;

public class BookingDto {
    private String customerName;
    private String vehicleName;
    private int customerId;
    private int vehicleId;
    private int id;
    private String startDate;
    private String lastDate;
    private String status;
    private String type;

    public BookingDto(String customerName, String vehicleName, int customerId, int vehicleId, int id, String startDate, String lastDate, String status, String type) {
        super();
        this.customerName = customerName;
        this.vehicleName = vehicleName;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.id = id;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.status = status;
        this.type = type;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
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



}
