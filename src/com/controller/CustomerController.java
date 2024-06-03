package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dto.VehicleLeaseDto;
import com.exception.CarNotFoundException;
import com.model.Customer;
import com.model.Lease;
import com.model.Vehicle;
import com.service.CustomerHistoryService;
import com.service.CustomerService;
import com.service.LeaseService;
import com.service.VehicleService;

public class CustomerController {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        VehicleService vehicleService = new VehicleService();
        LeaseService leaseService = new LeaseService();
        Scanner sc = new Scanner(System.in);

        // CUSTOMER ID CAN BE USED FOR FUNCTIONS RATHER THAN MANUALLY FINDING CUSTOMER ID FROM TABLE
        int customer_id=-1; // customer_id=-1 means no user is logged in
        String name = null;

        if(args[0] != null) {
            String loggedInUserId = args[0];

            int id = Integer.parseInt(loggedInUserId);

            try {
                Customer c1 = customerService.getCustomer(id);
                customer_id = c1.getId();
                name = c1.getFirst_name() + " " + c1.getLast_name();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Hello, " + name);

        while(true) {
            // CUSTOMER MENU
            System.out.println("");
            System.out.println("Press 1. All Cars");
            System.out.println("Press 2. Book Car");
            System.out.println("Press 3. My Deals");
            System.out.println("Press 4. Return vehicle");
            System.out.println("Press 5. Reviews");
            System.out.println("Press 0. to Exit");

            int input = sc.nextInt(); // INPUT FROM USER

            if(input == 0) { // FOR EXITING
                System.out.println("Logging out...");
                break;
            }

            switch(input) {

                case 1:
                    try {
                        List<Vehicle> vehicles = vehicleService.getAllCars();

                        if(vehicles.size() == 0) {
                            System.out.println("No vehicles available");
                            break;
                        }

                        System.out.println("VEHICLE_NAME  VEHICLE_MODEL  DAILY_RATE  PASSENGER_CAPACITY  ENGINE_CAPACITY  AVAILABILITY_STATUS");
                        for(Vehicle v : vehicles){
                            System.out.println(v.getVehicle_name() + " | " + v.getVehicle_model() + " | " + v.getDaily_rate() + " | " + v.getPassenger_capacity() + " | " + v.getEngine_capacity() + " | " + v.getAvailability_status());
                        }
                    } catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.println("+++++++++++++++++++++++++++++ BOOKING VEHICLE +++++++++++++++++++++++++++++++++");
                        List<Vehicle> vehicles = vehicleService.getAvailableCars(); // calling the function

                        if(vehicles.size() == 0) {
                            System.out.println("No vehicles available");
                            break;
                        }

                        System.out.println("ID  VEHICLE_NAME  VEHICLE_MODEL  DAILY_RATE  PASSENGER_CAPACITY  ENGINE_CAPACITY");
                        for(Vehicle v : vehicles){
                            System.out.println(v.getId() + " | " + v.getVehicle_name() + " | " + v.getVehicle_model() + " | " + v.getDaily_rate() + " | " + v.getPassenger_capacity() + " | " + v.getEngine_capacity());
                        }

                        // Choosing a vehicle and booking it
                        System.out.println("Please enter the vehicle ID"); // vehicle id
                        int vehicleId = sc.nextInt();
                        System.out.println("Please enter the starting date/(YYYY-MM-DD)");
                        sc.nextLine(); // starting date of the rent
                        String startingDate = sc.nextLine();
                        System.out.println("Please enter the ending date/(YYYY-MM-DD)"); // ending date of the rent
                        String endingDate = sc.nextLine();

                        System.out.println("If you are leasing a vehicle for more than a month then please enter Long Term else enter Short Term");
                        String type = sc.nextLine();

                        String status = "pending"; // lease status

                        Lease lease = new Lease(customer_id, vehicleId, startingDate, endingDate, status, type); // lease object

                        try {
                            int booking_status = leaseService.bookVehicle(lease);
                            if(booking_status==1) {
                                System.out.println("Vehicle has been booked successfully from " + lease.getStart_date() + " to " + lease.getEnd_date());
                            }
                        }catch(SQLException e) {
                            System.out.println(e.getMessage());
                        }catch(CarNotFoundException e) {
                            System.out.println(e.getMessage());
                        }

                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("_______________________________ Deals ___________________________________");
                    System.out.println("");
                    CustomerHistoryController.CustomerMenu(customer_id);
                    break;

                case 4:
                    try {
                        // since we can only return vehicle that we already have we are going to print those cars from lease that have status ongoing and where end date<=current_date
                        List<VehicleLeaseDto> vehicleLeaseList = vehicleService.getMyLeasedCars(customer_id); // these are cars that user has taken on lease
                        for(VehicleLeaseDto v : vehicleLeaseList) {
                            System.out.println(v.getId() + "  |  " + v.getVehicleName());
                        }
                        if(vehicleLeaseList.size()>0) {
                            System.out.println("Which car would you like to return, enter the id");
                            int returnId = sc.nextInt();

                            int status = vehicleService.returnVehicle(returnId);
                            if(status==1) {
                                System.out.println("Confirmed");
                            }
                        }else {
                            System.out.println("No vehicles to return");
                        }

                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:
                    ReviewController.Reviews(customer_id);
                    break;

                default:
                    System.out.println("Invalid input given, try again!!!");
            }

        }
    }

    public static void customerMenu(int id) {
        int user_id = id;
        String loggedInUserId = Integer.toString(user_id);
        String[] menu= {""};
        menu[0] = loggedInUserId;
        main(menu);
    }

}
