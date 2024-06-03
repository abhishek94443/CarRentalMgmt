package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dto.VehicleDto;
import com.exception.CarNotFoundException;
import com.exception.ResourceNotFoundException;
import com.model.Vehicle;
import com.service.VehicleService;

public class VehicleController {
    public static void main(String[] args) {
        VehicleService vehicleService = new VehicleService();
        Scanner sc = new Scanner(System.in);

        int vendorId = -1; // vendorId = -1 means no vendor is logged in

        if(args[0] != null) {
            String loggedInVendorId = args[0];
            vendorId = Integer.parseInt(loggedInVendorId);
        }else {
            String loggedInVendorId = "1";
            vendorId = Integer.parseInt(loggedInVendorId);
        }

        while(true) {
            System.out.println("----------Vehicle Ops-------------");
            System.out.println("Press 1. Insert Vehicle");
            System.out.println("Press 2. Delete Vehicle");
            System.out.println("Press 3. Change vehicle availability");
            System.out.println("Press 4. All Vehicles");
            System.out.println("Press 5. My Vehicles");
            System.out.println("Press 6. Update Daily Rate");
            System.out.println("Press 7. Average Daily Rate of my Vehicles ");
            System.out.println("Press 8. Display most leased vehicles");
            System.out.println("Press 0. to Exit");
            int input = sc.nextInt();
            if(input == 0) {
                System.out.println("Exiting Vehicles..");
                break;
            }

            switch(input) {
                case 1:
                    System.out.println("Enter Vehicle Name");
                    sc.nextLine();
                    String vehicleName = sc.nextLine();
                    System.out.println("Enter Vehicle Model (Year):");
                    //sc.nextLine();
                    String vehicleModel=sc.nextLine();
                    System.out.println("Enter Vehicle Reg. Date(yyyy-mm-dd) :");
                    String vehicleYear=sc.nextLine();
                    System.out.println("Enter Daily Rate");
                    float dailyRate=sc.nextFloat();
                    System.out.println("Enter Availability Status (0- not available, 1-available):");
                    int availabilityStatus=sc.nextInt();
                    System.out.println("Enter Passenger Capacity:");
                    int passengerCapacity=sc.nextInt();
                    System.out.println("Enter Vehicle Engine Capacity:");
                    sc.nextLine();
                    String engineCapacity=sc.nextLine();

                    /* Attach values to Object  */
                    Vehicle vehicle = new Vehicle(vehicleName, vehicleModel, vehicleYear, dailyRate, availabilityStatus, passengerCapacity, engineCapacity, vendorId);
                    try {
                        int status = vehicleService.insert(vehicle);
                        if(status == 1)
                            System.out.println("Vehicle added successfully.");
                        else
                            System.out.println("Insert op failed");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:
                    try {
                        List<Vehicle> list = vehicleService.findAllfromVendor(vendorId);

                        if(list.size() == 0) {
                            System.out.println("No data");
                            break;
                        }

                        for(Vehicle v : list) {
                            System.out.println(v);
                        }
                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println("Enter Vehicle ID");
                    try {
                        vehicleService.deleteByid(sc.nextInt());
                        System.out.println("Vehicle deleted..");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    try {
//					display all vehicle from the vendor
                        List<Vehicle> vendorVehicle = vehicleService.findAllfromVendor(vendorId);

                        if(vendorVehicle.size() == 0) {
                            System.out.println("No data");
                            break;
                        }

                        for(Vehicle v : vendorVehicle) {
                            System.out.println(v.toString());
                        }
                        System.out.println("Enter Vehicle ID");
                        int vehicleId = sc.nextInt();
                        System.out.println("Enter availability status (0 - not available / 1 - available)");
                        int availability = sc.nextInt();
                        vehicleService.changeAvailabilityStatus(vehicleId, availability);
                        System.out.println("Vehicle availability status updated");
                    }  catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        List<Vehicle> allVehicles = vehicleService.findAll();

                        if(allVehicles.size() == 0) {
                            System.out.println("No data");
                            break;
                        }

                        for(Vehicle v : allVehicles) {
                            System.out.println(v.toString());
                        }
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    try {
                        List<Vehicle> vendorVehicle = vehicleService.findAllfromVendor(vendorId);
                        for(Vehicle v : vendorVehicle) {
                            System.out.println(v.toString());
                        }

                    }catch(SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    try {
                        //display all vehicle from the vendor
                        System.out.println("My Vehicles...");
                        List<Vehicle> list = vehicleService.findAllfromVendor(vendorId);

                        if(list.size() == 0) {
                            System.out.println("No data");
                            break;
                        }

                        for(Vehicle v : list) {
                            System.out.println(v.toString());
                        }
                        System.out.println("Enter Vehicle ID");
                        int vehicleID = sc.nextInt();
                        System.out.println("Enter new Daily Rate:");
                        vehicleService.updateDailyRate(vehicleID, sc.nextDouble());
                        System.out.println("Vehicle Daily Rate Updated...");
                    } catch (CarNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Average Daily Rate of my Vehicles: ");
                    try {
                        VehicleDto vehicleDto = vehicleService.getAvgDailyRate(vendorId);
                        System.out.println("--------------------------------------------------------\n");
                        System.out.format("%15s%25s", "Vendor ID", "Average Daily Rate");
                        System.out.println("\n--------------------------------------------------------");
                        System.out.format("%15d%25.2f\n", vendorId, vehicleDto.getValue());
                        System.out.println("--------------------------------------------------------\n");
                    }
                    catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 8:
                    System.out.println("Most Leased Vehicles... ");
                    try {
                        List<VehicleDto> list = vehicleService.getMostLeasedVehicle(vendorId);

                        if(list.size() == 0) {
                            System.out.println("No data");
                            break;
                        }

                        for(VehicleDto b : list){
                            System.out.println("--------------------------------------------------------\n");
                            System.out.format("%15s%35s", "Vehicle", "Number of Leasings");
                            System.out.println("\n--------------------------------------------------------");
                            System.out.format("%15s%25.2f\n", b.getCriteria(), b.getValue());
                            System.out.println("--------------------------------------------------------\n");
                        }
                    }
                    catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }

    }

    public static void VehicleMenu(int id) {
        int vendorId = id;
        String loggedInUserId = Integer.toString(vendorId);
        String[] menu= {""};
        menu[0] = loggedInUserId;
        main(menu);
    }
}
