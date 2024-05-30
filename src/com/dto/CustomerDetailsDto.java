package com.dto;

// This model is used to display all the customers of a particular vendor
public class CustomerDetailsDto {
    private String customer;
    private String phoneNumber;
    private String drivingLicense;
    private String duration;
    private String vehicleName;

    public CustomerDetailsDto(String customer, String phoneNumber, String drivingLicense, String duration,
                              String vehicleName) {
        super();
        this.customer = customer;
        this.phoneNumber = phoneNumber;
        this.drivingLicense = drivingLicense;
        this.duration = duration;
        this.vehicleName = vehicleName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }




}
