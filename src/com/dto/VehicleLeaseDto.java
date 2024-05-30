package com.dto;

public class VehicleLeaseDto {
    private int id; // lease id
    private String vehicleName;

    public VehicleLeaseDto(int id, String vehicleName) {
        super();
        this.id = id;
        this.vehicleName = vehicleName;
    }

    public VehicleLeaseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    @Override
    public String toString() {
        return "VehicleLeaseDto [id=" + id + ", vehicleName=" + vehicleName + "]";
    }

}
