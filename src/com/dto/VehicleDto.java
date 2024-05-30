package com.dto;

public class VehicleDto {
    private String criteria;
    private double value;

    public VehicleDto(String criteria, double value) {
        super();
        this.criteria = criteria;
        this.value = value;
    }

    public VehicleDto() {
        super();
    }

    public String getCriteria() {
        return criteria;
    }
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VehicleDto [criteria=" + criteria + ", value=" + value + "]";
    }
}