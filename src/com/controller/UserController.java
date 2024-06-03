package com.controller;
//Run main method of this class to start the application
//package com.;

import java.sql.SQLException;
import java.util.Scanner;

import com.exception.InvalidCredentialsException;
import com.model.Customer;
import com.model.User;
import com.model.Vendor;
import com.service.CustomerService;
import com.service.UserService;
import com.service.VendorService;

public class UserController {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserService();
        CustomerService customerService = new CustomerService();
        VendorService vendorService = new VendorService();

        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("----------CAR RENTAL SYSTEM-------------"); // MAIN MENU
            System.out.println("Press 1. Sign up ");
            System.out.println("Press 2. Login ");
            System.out.println("Press 0. to Exit");

            int input = sc.nextInt(); // INPUT FROM USER

            if(input == 0) { // FOR EXITING
                System.out.println("Leaving Car rental");
                break;
            }

            switch(input) {
                case 1: // FOR SIGN UP
                    System.out.println("Enter email address");
                    sc.nextLine();
                    String email = sc.nextLine();
                    System.out.println("Enter password (should contain atlease 1 alphabet, 1 number and 1 symbol, minimum 8 letters)");
                    String password = sc.nextLine();

                    // PASSWORD VALIDATION
                    boolean hasDigit = false;
                    boolean hasLowercase = false;
                    boolean hasUppercase = false;
                    boolean hasSpecialSymbol = false;

                    for (char c : password.toCharArray()) {
                        if (Character.isDigit(c)) {
                            hasDigit = true;
                        } else if (Character.isLowerCase(c)) {
                            hasLowercase = true;
                        } else if (Character.isUpperCase(c)) {
                            hasUppercase = true;
                        } else if (!Character.isWhitespace(c)) {
                            hasSpecialSymbol = true;
                        }
                    }
                    if(password.length() < 8 || !hasDigit || !hasLowercase || !hasUppercase || !hasSpecialSymbol) {
                        System.out.println("Wrong password format...try again");
                        break;
                    }
                    // ------------------------------------------------------------------------ //

                    System.out.println("Enter your role (Customer/Vendor)");
                    String role = sc.nextLine();
                    System.out.println("Enter username");
                    String user_name = sc.nextLine();
                    User user = new User(email, password, role, user_name);

                    try {
                        String status = userService.insert(user); // INSERTION INTO USER TABLE
                        if(status == null) {
                            System.out.println("Sign up failed");
                        } else {                                 // IF INSERTION SUCCESSFUL THEN
                            String[] parts = status.split("  "); // grabbing email and role returned from the function in status variable
                            String created_user_email = parts[0];
                            String created_user_role = parts[1];

                            int user_id = userService.getId(created_user_email); // find userid from the grabbed email

                            System.out.println("-------------- Please complete your profile info ---------------");
                            if(created_user_role.equalsIgnoreCase("Customer")) { // DETAILS FOR CUSTOMER TALE
                                System.out.println("Enter first name");
                                String first_name = sc.nextLine();
                                System.out.println("Enter last_name");
                                String last_name = sc.nextLine();
                                System.out.println("Enter your phone number");
                                String phone_number = sc.nextLine();
                                System.out.println("Enter your city");
                                String city = sc.nextLine();
                                System.out.println("To rent a vehicle, you need a driving license, please enter your driving license number");
                                String driving_license = sc.nextLine();

                                Customer c1 = new Customer(first_name, last_name, phone_number, city, user_id, driving_license);

                                try {
                                    int customer_signup_status = customerService.insert(c1);
                                    if(customer_signup_status == 1) {
                                        System.out.println("Account successfully created, you can LOGIN now");
                                    }
                                }catch(SQLException e) {
                                    System.out.println(e.getMessage());
                                }


                            }else if(created_user_role.equalsIgnoreCase("Vendor") ) { // DETAILS FOR VENDOR TALE
                                System.out.println("Enter your name");
                                String name = sc.nextLine();
                                System.out.println("Please enter your identity proof ie. Aadhar card, PAN card, Driving License");
                                String identity_proof = sc.nextLine();
                                System.out.println("Enter your phone number");
                                String phone_number = sc.nextLine();

                                Vendor v1 = new Vendor(name, identity_proof, phone_number, user_id);

                                try {
                                    int vendor_signup_status = vendorService.insert(v1);
                                    if(vendor_signup_status == 1) {
                                        System.out.println("Account successfully created, you can LOGIN now");
                                    }
                                }catch(SQLException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            else {
                                System.out.println("Error completing the profile");
                            }
                        }
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: // FOR LOGIN
                    try {
                        System.out.println("Enter username");
                        sc.nextLine();
                        String username = sc.nextLine();
                        System.out.println("Enter password");
                        String loginPassword = sc.nextLine();
                        /* go to DB and check if this credentials are valid, if yes then return object*/

                        User userObj = userService.login(username,loginPassword);
                        if(userObj.getRole().equalsIgnoreCase("CUSTOMER")) {
                            CustomerController.customerMenu(userObj.getId());
                        }
                        else if(userObj.getRole().equalsIgnoreCase("VENDOR")) {
                            VendorController.VendorMenu(userObj.getId());
                        }
                        else{
                            System.out.println("----------HELLO, ADMIN-------------");
                            AdminController.AdminMenu();
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidCredentialsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid input given, try again!!!");

            }

        }
        sc.close();
    }
}
