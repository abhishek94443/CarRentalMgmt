package com.model;

public class Vendor {
    private int id;
    private String name;
    private String identity_proof;
    private String phone_number;
    private int user_id;
    private double commission;

    public Vendor(String name, String identity_proof, String phone_number, int user_id) {
        super();
        this.name = name;
        this.identity_proof = identity_proof;
        this.phone_number = phone_number;
        this.user_id = user_id;
    }

    public Vendor(int id, String name, String identity_proof, String phone_number, int user_id, double commission) {
        super();
        this.id = id;
        this.name = name;
        this.identity_proof = identity_proof;
        this.phone_number = phone_number;
        this.user_id = user_id;
        this.commission = commission;
    }

    public Vendor() {
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
    public String getIdentity_proof() {
        return identity_proof;
    }
    public void setIdentity_proof(String identity_proof) {
        this.identity_proof = identity_proof;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public double getCommission() {
        return commission;
    }
    public void setCommission(double commission) {
        this.commission = commission;
    }

}
