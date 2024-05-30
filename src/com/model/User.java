package com.model;

public class User {
    private int id;
    private String email_address;
    private String password;
    private String role;
    private String user_name;

    public User(String email_address, String password, String role, String user_name) {
        super();
        this.email_address = email_address;
        this.password = password;
        this.role = role;
        this.user_name = user_name;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public String toString() {
        return "User [email_address=" + email_address + ", password=" + password + ", role=" + role
                + ", user_name=" + user_name + "]";
    }

}
