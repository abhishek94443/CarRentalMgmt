package com.dto;

// This model is used by vendor, where he sees the vehicles where the customer has completed the deal from his side. so the vendor collects the vehicle back, changes the status to completed in the lease table and push the record in the customer_history table
public class ReadyToReturnDto {
    private int dealId;
    private int customerId;
    private String customerName;
    private String phoneNumber;
    private String vehicleName;
    private int vehicleId;
    private String lastDate;


    public ReadyToReturnDto(int dealId, int customerId, String customerName, String phoneNumber, String vehicleName, int vehicleId,
                            String lastDate) {
        super();
        this.dealId = dealId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.vehicleName = vehicleName;
        this.vehicleId = vehicleId;
        this.lastDate = lastDate;
    }

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

}
