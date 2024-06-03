package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dto.BookingDto;
import com.dto.ParticularVendorDto;
import com.dto.VendorProfitDto;
import com.model.Customer;
import com.model.Vendor;
import com.service.CustomerService;
import com.service.LeaseService;
import com.service.VehicleService;
import com.service.VendorService;

public class AdminController {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        VendorService vendorService = new VendorService();
        LeaseService leaseService = new LeaseService();
        VehicleService vehicleService = new VehicleService();
        Scanner sc = new Scanner(System.in);

        while(true) {
            // ADMIN MENU
            System.out.println("Press 1. See all Customers");
            System.out.println("Press 2. See all Vendors");
            System.out.println("Press 3. See all Bookings");
            System.out.println("Press 4. View a particular Vendor");
            System.out.println("Press 5. View a particular Customer");
            System.out.println("Press 6. Blacklist a customer");
            System.out.println("Press 7. Block a vendor");
            System.out.println("Press 8. Decide Vendor commission");
            System.out.println("Press 9. Profits from vendors");
            System.out.println("Press 0. Exit");

            int input = sc.nextInt(); // INPUT FROM USER

            if(input == 0) { // FOR EXITING
                System.out.println("Signing out");
                break;
            }

            switch(input) {

                case 1:
                    System.out.println("");
                    System.out.println("**************************************** ALL CUSTOMERS *************************************************");
                    System.out.println("");
                    try {
                        List<Customer> customers = customerService.getAllCustomers();
                        System.out.println("FIRST_NAME  LAST_NAME  PHONE_NUMBER  CITY  DRIVING_LICENSE  USER_ID");
                        System.out.println("-------------------------------------------------------------");
                        for(Customer c : customers){
                            System.out.println(c.getFirst_name() + " " + c.getLast_name() + " | " + c.getPhone_number() + " | " + c.getCity() + " | " + c.getDriving_license() + " | " + c.getUser_id());
                        }
                        System.out.println("");
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("");
                    System.out.println("**************************************** ALL VENDORS *************************************************");
                    System.out.println("");
                    try {
                        List<Vendor> vendors = vendorService.getAllVendors();
                        System.out.println("NAME   PHONE_NUMBER   IDENTITY PROOF   COMMISSION   USER_ID");
                        System.out.println("-------------------------------------------------------------");
                        for(Vendor v : vendors){
                            System.out.println(v.getName() + " | " + v.getPhone_number() + " | " + v.getIdentity_proof() + " | " + v.getCommission() + " | " + v.getUser_id());
                        }
                        System.out.println("");
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("");
                    System.out.println("**************************************** ALL BOOKINGS *************************************************");
                    System.out.println("");
                    try {
                        List<BookingDto> bookings = leaseService.getAllBookings();
                        System.out.println("CUSTOMER_ID  CUSTOMER_NAME  VEHICLE_ID  VEHICLE_NAME  BOOKING_ID  START_DATE  END_DATE  STATUS  TYPE");
                        System.out.println("-----------------------------------------------------------------------------------------------------");
                        for(BookingDto b : bookings){
                            System.out.println(b.getCustomerId() + " " + b.getCustomerName() + " " + b.getVehicleId() + " " + b.getVehicleName() + " " + b.getId() + " " + b.getStartDate() + " " + b.getLastDate() + " " + b.getStatus() + " " + b.getType());
                            System.out.println(b.getCustomerName() + " HAS BOOKED " + b.getVehicleName() + " FROM " + b.getStartDate() + " TO " + b.getLastDate());
                            System.out.println("");
                        }
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("");
                    System.out.println("**************************************** All Vendors *************************************************");
                    System.out.println("");
                    try {
                        List<Vendor> vendors = vendorService.getAllVendors();
                        System.out.println("ID  NAME");
                        for(Vendor v : vendors){
                            System.out.println(v.getId() + " " + v.getName());
                        }

                        System.out.println("");
                        System.out.println("Enter the vendor Id to view that vendor");

                        int id = sc.nextInt();

                        try {
                            ParticularVendorDto particularVendor = vendorService.getVendorById(id);

                            System.out.println("VENDOR ID: " + particularVendor.getId());
                            System.out.println("NAME: " + particularVendor.getName());
                            System.out.println("IDENTITY PROOF: " + particularVendor.getIdentityProof());
                            System.out.println("PHONE NUMBER: " + particularVendor.getPhoneNumber());
                            System.out.println("COMMISSION: " + particularVendor.getCommission());
                            System.out.println("USER ID: " + particularVendor.getUserId());
                            System.out.println("OWNS " + particularVendor.getNumberOfCars() + " NUMBER OF CARS");
                            System.out.println("");
                        }catch(SQLException e) {
                            System.out.println(e.getMessage());
                        }

                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("");
                    System.out.println("**************************************** All Customers *************************************************");
                    System.out.println("");
                    try {
                        List<Customer> customers = customerService.getAllCustomers();
                        System.out.println("CUSTOMER_ID FIRST_NAME LAST_NAME");
                        System.out.println("--------------------------------");
                        for(Customer c : customers){
                            System.out.println(c.getId() + " " + c.getFirst_name() + " " + c.getLast_name());
                        }
                        System.out.println("");
                        System.out.println("Enter the CUSTOMER ID to view that customer");
                        int id = sc.nextInt();
                        try {
                            Customer c1 = customerService.particularCustomer(id);
                            System.out.println("CUSTOMER NAME: "+ c1.getFirst_name()+" "+c1.getLast_name());
                            System.out.println("CITY: "+ c1.getCity());
                            System.out.println("PHONE NUMBER: "+ c1.getPhone_number());
                            System.out.println("DRIVING LICENSE: "+ c1.getDriving_license());
                            System.out.println("");
                        }catch(SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("");
                    System.out.println("**************************************** Blacklist a customer *************************************************");
                    System.out.println("");

                    try {
                        List<Customer> customers = customerService.getAllCustomers();
                        System.out.println("CUSTOMER_ID CUSTOMER_NAME");
                        System.out.println("--------------------------------");
                        for(Customer c : customers){
                            System.out.println(c.getId() + " " + c.getFirst_name() + " " + c.getLast_name());
                        }
                        System.out.println("");
                        System.out.println("Enter the CUSTOMER ID to blacklist the customer");
                        int id = sc.nextInt();
                        System.out.println("Are you sure you want to blacklist the customer with customer id: " + id);
                        System.out.println("Enter YES to confirm");
                        sc.nextLine();
                        String ans = sc.nextLine();
                        if(ans.equals("YES")) {
                            try {
                                int status = customerService.blacklistCustomer(id);
                                if(status == 1) {
                                    System.out.println("Customer has been blacklisted");
                                }
                            }catch(SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else {
                            break;
                        }
                        System.out.println("");
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 7:
                    System.out.println("");
                    System.out.println("**************************************** Block a Vendor *************************************************");
                    System.out.println("");

                    try {
                        List<Vendor> vendors = vendorService.getAllVendors();
                        System.out.println("ID   NAME   PHONE_NUMBER   IDENTITY PROOF   COMMISSION   USER_ID");
                        System.out.println("-------------------------------------------------------------");
                        for(Vendor v : vendors){
                            System.out.println(v.getId() + " | " + v.getName() + " | " + v.getPhone_number() + " | " + v.getIdentity_proof() + " | " + v.getCommission() + " | " + v.getUser_id());
                        }

                        System.out.println("");
                        System.out.println("Enter the VENDOR ID to block that Vendor");
                        int id = sc.nextInt();
                        System.out.println("Are you sure you want to blacklist the vendor with vendor id: " + id);
                        System.out.println("Enter YES to confirm");
                        sc.nextLine();
                        String ans = sc.nextLine();
                        if(ans.equals("YES")) {
                            try {
                                int status = vendorService.blockVendor(id);
                                if(status == 1) {
                                    System.out.println("Vendor has been blocked");
                                    // deleting vehicles by the deleted vendor
                                    try {
                                        int status1 = vehicleService.deleteVehicle(id); // soft delete
                                        if(status == 1) {
                                            System.out.println("The vendor's vehicle has gone unavailable");
                                        }

                                    }catch(SQLException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }catch(SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else {
                            break;
                        }
                        System.out.println("");
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 8:
                    System.out.println("");
                    System.out.println("**************************************** ALL VENDORS *************************************************");
                    System.out.println("");
                    try {
                        List<Vendor> vendors = vendorService.getAllVendors();
                        System.out.println("ID   NAME   COMMISSION");
                        System.out.println("-----------------------");
                        for(Vendor v : vendors){
                            System.out.println(v.getId() + " | " + v.getName() + " | " + v.getCommission());
                        }
                        System.out.println("");
                        System.out.println("Enter the VENDOR ID to edit the commission of that vendor");
                        int id = sc.nextInt();
                        System.out.println("Enter the COMMISSION Value for that vendor");
                        double c = sc.nextDouble();

                        try {
                            int status = vendorService.changeCommission(id,c);
                            if(status == 1) {
                                System.out.println("Commission has been updated");
                            }

                        }catch(SQLException e) {
                            System.out.println(e.getMessage());
                        }

                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 9:
                    try {
                        List<VendorProfitDto> vpd = vendorService.getProfits();
                        System.out.println("VENDOR NAME  PROFIT_PER_DAY  PROFIT_PER_MONTH");
                        for(VendorProfitDto v: vpd) {
                            System.out.println(v.getName() + " | " + v.getProfitPerDay() + " | " + v.getProfitPerMonth());
                        }
                        System.out.println("");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid input given, try again!!!");
            }

        }


    }
    public static void AdminMenu() {
        String[] menu= {""};
        main(menu);
    }

}
