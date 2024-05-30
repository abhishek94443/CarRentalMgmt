package com.dto;

// using this model to display the upcoming deals for vendor
public class CustomerLeaseDto {
    private String customerName;
    private String dealDate;
    private String vehicleName;
    private int vehicleId;
    private int dealId;

    public CustomerLeaseDto(String customerName, String dealDate, String vehicleName, int vehicleId, int dealId) {
        super();
        this.customerName = customerName;
        this.dealDate = dealDate;
        this.vehicleName = vehicleName;
        this.vehicleId = vehicleId;
        this.dealId = dealId;
    }

    public CustomerLeaseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
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

    public int getDealId() {
        return dealId;
    }

    public void setDealId(int dealId) {
        this.dealId = dealId;
    }


}
