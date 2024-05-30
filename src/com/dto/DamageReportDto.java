package com.dto;

public class DamageReportDto {
    private String firstName;
    private String lastName;
    private String anyDamageReported;

    public DamageReportDto(String firstName, String lastName, String anyDamageReported) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.anyDamageReported = anyDamageReported;
    }

    public DamageReportDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAnyDamageReported() {
        return anyDamageReported;
    }
    public void setAnyDamageReported(String anyDamageReported) {
        this.anyDamageReported = anyDamageReported;
    }



}
