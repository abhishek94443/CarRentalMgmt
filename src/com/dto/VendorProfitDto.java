package com.dto;

public class VendorProfitDto {
    private String name;
    private double profitPerDay;
    private double profitPerMonth;

    public VendorProfitDto(String name, double profitPerDay, double profitPerMonth) {
        super();
        this.name = name;
        this.profitPerDay = profitPerDay;
        this.profitPerMonth = profitPerMonth;
    }

    public VendorProfitDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getProfitPerDay() {
        return profitPerDay;
    }
    public void setProfitPerDay(double profitPerDay) {
        this.profitPerDay = profitPerDay;
    }
    public double getProfitPerMonth() {
        return profitPerMonth;
    }
    public void setProfitPerMonth(double profitPerMonth) {
        this.profitPerMonth = profitPerMonth;
    }

    @Override
    public String toString() {
        return "VendorProfitDto [name=" + name + ", profitPerDay=" + profitPerDay + ", profitPerMonth=" + profitPerMonth
                + "]";
    }
}
