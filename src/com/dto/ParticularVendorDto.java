package com.dto;

public class ParticularVendorDto {
    private int id;
    private String name;
    private String identityProof;
    private String phoneNumber;
    private double commission;
    private int numberOfCars;
    private int userId;


    public ParticularVendorDto(int id, String name, String identityProof, String phoneNumber, double commission, int numberOfCars, int userId) {
        super();
        this.id = id;
        this.name = name;
        this.identityProof = identityProof;
        this.phoneNumber = phoneNumber;
        this.commission = commission;
        this.numberOfCars = numberOfCars;
        this.userId = userId;
    }

    public ParticularVendorDto() {
        super();
        // TODO Auto-generated constructor stub
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityProof() {
        return identityProof;
    }

    public void setIdentityProof(String identityProof) {
        this.identityProof = identityProof;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "ParticularVendorDto [id=" + id + ", name=" + name + ", identityProof=" + identityProof
                + ", phoneNumber=" + phoneNumber + ", commission=" + commission + ", numberOfCars=" + numberOfCars
                + ", userId=" + userId + "]";
    }

}
