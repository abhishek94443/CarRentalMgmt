package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dto.CustomerDetailsDto;
import com.dto.CustomerLeaseDto;
import com.dto.ReadyToReturnDto;
import com.dto.VehicleReviewCustomerDto;
import com.exception.WrongInformationException;
import com.model.CustomerHistory;
import com.model.Vendor;
import com.service.CustomerHistoryService;
import com.service.CustomerService;
import com.service.LeaseService;
import com.service.VehicleService;
import com.service.VendorService;

public class VendorController {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VendorService vendorService = new VendorService();
        LeaseService leaseService = new LeaseService();
        CustomerService customerService = new CustomerService();
        CustomerHistoryService customerHistoryService = new CustomerHistoryService();
        VehicleService vehicleService = new VehicleService();

        int vendorId = -1; // vendorId = -1 means no vendor is logged in
        String name = null;

        if(args[0] != null) {
            String loggedInUserId = args[0];

            int id = Integer.parseInt(loggedInUserId);

            try {
                Vendor v1 = vendorService.getVendor(id);
                vendorId = v1.getId();
                name = v1.getName();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }


        System.out.println("Hello, " + name);

        while(true) { // VENDOR MENU
            System.out.println("Press 1. VEHICLES");
            System.out.println("Press 2. Upcoming bookings");
            System.out.println("Press 3. My customer details");
            System.out.println("Press 4. Update customer history");
            System.out.println("Press 5. Reviews on my vehicles");
            System.out.println("Press 0. Exit");

            int input = sc.nextInt(); // INPUT FROM USER

            if(input == 0) { // FOR EXITING
                System.out.println("Logging out...");
                break;
            }

            switch(input) {

                case 1:
                    System.out.println("_____________________________________________VEHICLES_____________________________________________");
                    System.out.println("");
                    VehicleController.VehicleMenu(vendorId);
                    break;

                case 2:
                    try {
                        List<CustomerLeaseDto> list = leaseService.getMyUpcomingDeals(vendorId);

                        System.out.println("CUSTOMER   DATE   VEHICLE   VEHICLE-ID   DEAL-ID");
                        for(CustomerLeaseDto cust : list) {
                            System.out.println(cust.getCustomerName() + "  " + cust.getDealDate() + "  " + cust.getVehicleName() + "  " + cust.getVehicleId() + "  " + cust.getDealId());
                        }
                        if(list.size()>0) {
                            System.out.println("Do you want to deliver the vehicle for any deal? (yes/no)");
                            sc.nextLine();
                            String ans = sc.nextLine();
                            if(ans.equalsIgnoreCase("YES")) {
                                System.out.println("Please enter the DEAL-ID from the above list");
                                int dealId = sc.nextInt();
                                System.out.println("Please enter the VEHICLE-ID from the above list");
                                int vehicleId = sc.nextInt();
                                int status = leaseService.deliverVehicle(dealId);
                                if(status == 1) {
                                    try {
                                        int changeAvailabilityStatus = vehicleService.changeAvailabilityStatus(vehicleId, 0);
                                        if(changeAvailabilityStatus == 1) {
                                            System.out.println("vehicle made unavailable");
                                        }
                                    }catch(SQLException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                            }else {
                                break;
                            }
                        }
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        List<CustomerDetailsDto> list = customerService.getDetailsForVendor(vendorId);
                        System.out.println("CUSTOMER   PHONE-NUMBER   DURATION   DRIVING-LICENSE   VEHICLE");

                        for(CustomerDetailsDto details : list) {
                            System.out.println(details.getCustomer() + "  " + details.getPhoneNumber() + "  " + details.getDuration() + "  " + details.getDrivingLicense() + "  " + details.getVehicleName());
                        }

                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:
                    try {
                        List<ReadyToReturnDto> list = vendorService.getBackVehicle(vendorId); //  ALL THE VEHICLES THAT ARE IN READY TO BE RETURNED STATE BY CUSTOMER
                        System.out.println("Deal-Id   Customer-Id   Customer-Name   Phone-number   Vehicle   Vehicle-Id   Last-date");
                        for(ReadyToReturnDto details : list) {
                            System.out.println(details.getDealId() + " " + details.getCustomerId() + " " + details.getCustomerName() + " " + details.getPhoneNumber() + " " + details.getVehicleName() + " " + details.getVehicleId() + " " + details.getLastDate());
                        }
                        if(list.size()>=1) {
                            System.out.println("Please complete the following details to complete the deal");
                            System.out.println("");
                            System.out.println("Enter the Deal Id from the above list");
                            int dealId = sc.nextInt();
                            System.out.println("Enter the customer Id from the above list");
                            int customerId = sc.nextInt();
                            System.out.println("Enter the vehicle Id from the above list");
                            int vehicleId = sc.nextInt();
                            System.out.println("What was the discount given to this customer");
                            double discount = sc.nextDouble();
                            System.out.println("The final amount paid by the customer");
                            double finalAmount = sc.nextDouble();
                            System.out.println("Starting mileage of the vehicle");
                            int startMileage = sc.nextInt();
                            System.out.println("Current mileage of the vehicle");
                            int currentMileage = sc.nextInt();
                            System.out.println("If the customer has extended the last date, please enter the extra amount");
                            double lateReturnFees = sc.nextDouble();
                            System.out.println("Is the vehicle damaged? (yes/no)");
                            sc.nextLine();
                            String damaged = sc.nextLine();
                            CustomerHistory customerHistory = new CustomerHistory(discount, lateReturnFees, startMileage, currentMileage, damaged, customerId, vehicleId, finalAmount);

                            try {
                                int status = customerHistoryService.insertIntoHistory(customerHistory); // SET THE STATUS OF THE LEASE TO BE COMPLETED
                                if(status==1) {
                                    int leaseCompleted = leaseService.leaseCompleted(dealId);
                                    if(leaseCompleted == 1) {
                                        int vehicleAvailable = vehicleService.changeAvailabilityStatus(vehicleId, 1);
                                        if(vehicleAvailable == 1) {
                                            System.out.println("DEAL COMPLETED");
                                        }
                                    }
                                }
                            }catch(WrongInformationException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    try {
                        List <VehicleReviewCustomerDto> list = vendorService.getReviewsOnVehicles(vendorId);

                        System.out.println("VEHICLE   DESCRIPTION   RATINGS   CUSTOMER");
                        for(VehicleReviewCustomerDto review : list) {
                            System.out.println(review.getVehicleName() + "  " + review.getDescription() + "  " + review.getRatings() + "  " + review.getCustomer());
                        }
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                default:
                    System.out.println("Invalid input given, try again!!!");
            }

        }

    }


    public static void VendorMenu(int id) {
        int vendorId = id;
        String loggedInUserId = Integer.toString(vendorId);
        String[] menu= {""};
        menu[0] = loggedInUserId;
        main(menu);
    }

}
