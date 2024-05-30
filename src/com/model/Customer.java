package com.model;

public class Customer {
    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String city;
    private int user_id;
    private String driving_license;
    public Customer(String first_name, String last_name, String phone_number, String city, int user_id,
                    String driving_license) {
        super();
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.city = city;
        this.user_id = user_id;
        this.driving_license = driving_license;
    }

    public Customer(int id, String first_name, String last_name, String phone_number, String city, int user_id, String driving_license) {
        super();
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.city = city;
        this.user_id = user_id;
        this.driving_license = driving_license;
    }

    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDriving_license() {
        return driving_license;
    }

    public void setDriving_license(String driving_license) {
        this.driving_license = driving_license;
    }
}
