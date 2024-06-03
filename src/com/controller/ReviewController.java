package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dto.CustomerHistoryDto;
import com.dto.ReviewDto;
import com.dto.VehicleReviewDto;
import com.exception.InvalidRatingsException;
import com.exception.NoReviewForParticularVehicleException;
import com.exception.ResourceNotFoundException;
import com.model.Customer;
import com.model.Review;
import com.model.Vehicle;
import com.service.CustomerHistoryService;
import com.service.CustomerService;
import com.service.ReviewService;
import com.service.VehicleService;

public class ReviewController {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        CustomerHistoryService customerHistoryService = new CustomerHistoryService();
        ReviewService reviewService = new ReviewService();
        VehicleService vehicleService = new VehicleService();
        Scanner sc = new Scanner(System.in);

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
        System.out.println("");


        while (true) {
            System.out.println("Press 1.Enter your review");
            System.out.println("Press 2.Display All Reviews");
            System.out.println("Press 3.Delete the review");
            System.out.println("Press 4.Update your ratings");
            System.out.println("Press 5.View average ratings for Each vehicle");
            System.out.println("Press 6.View reviews for a particular vehicle");
            System.out.println("Press 0.Exit");
            int input = sc.nextInt();
            if (input == 0) {
                System.out.println("Exiting review Module..");
                break;
            }
            switch (input) {
                case 1:

                    try {
                        Review review = new Review();
                        List<CustomerHistoryDto> customerHistorylist = customerHistoryService.findAll(customer_id);
                        System.out.println("VEHICLE-ID   VEHICLE-NAME   AMOUNT   DISCOUNT   DAMAGED");
                        System.out.println("");
                        for(CustomerHistoryDto c:customerHistorylist) {
                            System.out.println(c.getVehicleId() + "  " + c.getVehicleName() + "  " + c.getFinalAmount() + "  " + c.getDiscount() + "  " + c.getDamageReported());
                        }
                        System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Enter the vehicle ID that you want to provide review:");
                        review.setVehicle_id(sc.nextInt());
                        review.setCustomer_id(customer_id);
                        // reading description and assigning to review obj
                        System.out.println("Enter Your review in description");
                        sc.nextLine();
                        review.setDescription(sc.nextLine());
                        // reading ratings and assigning it to the review obj
                        System.out.println("Enter your ratings from '1' to '5'");
                        int rating = sc.nextInt();
                        review.setRatings(rating);
                        // getting status of insertion as 1 or 0 from (service)class
                        int status = reviewService.save(review);

                        if (status == 1) {
                            System.out.println("--Your review is saved succesfully--");
                        } else {
                            System.out.println("Your review couldn't saved");
                        }
                    } catch (SQLException e) {
                        // if there is an sql exception it displays the message
                        System.out.println(e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        // if the id is not found in customer or vehicle tables it iwill display message
                        System.out.println(e.getMessage());
                    } catch (InvalidRatingsException e) {
                        // if the ratings is not between 1 to 5 it will display message
                        System.out.println(e.getMessage());
                    }
                    break;


                case 2:

                    try {
                        // displaying all the reviews provided by customer for vehicles
                        System.out.println("------------Displaying All Reviews------------");
                        List<VehicleReviewDto> reviewlist = reviewService.findAll();

                        System.out.println("VEHICLE  -  DESCRIPTION  -  RATINGS");

                        for(VehicleReviewDto v : reviewlist) {
                            System.out.println(v.getVehicle_name() + "  " + v.getDescription() + "  " + v.getRatings());
                        }

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    }
                    break;

                case 3:
                    // if they want to delete their entire review use this case
                    try {
                        // displays all the review to choose their review id which is need to be deleted
                        System.out.println("*******Displaying ALL Reviews To choose Id******");
                        System.out.println("\n");
                        List<VehicleReviewDto> reviewlist = reviewService.findReviewByCustomerId(customer_id);
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                        System.out.format("%30s%40s%50s%50s%40s","Review Id", "Vehicle Id", "Vehicle name", "Description", "Ratings Of Vehicle");
                        System.out.println(
                                "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        for (VehicleReviewDto r : reviewlist) {
                            System.out.format("%30s%40s%50s%50s%40s", r.getReviewId(), r.getVehicleId(), r.getVehicle_name(), r.getDescription(), r.getRatings());
                            System.out.println("\n");
                        }
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("\n");
                        System.out.println("Enter your review id");
                        reviewService.DeleteById(sc.nextInt());
                        System.out.println("Your review is Deleted successfully.....");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    // displays all the review to choose their review id in which the rating need to
                    // be updated
                    System.out.println("*******Displaying ALL Reviews To choose Id******");
                    System.out.println("\n");
                    try {
                        List<VehicleReviewDto> reviewlist = reviewService.findReviewByCustomerId(customer_id);

                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                        System.out.format("%30s%40s%50s%50s%40s","Review Id", "Vehicle Id", "Vehicle name", "Description", "Ratings Of Vehicle");
                        System.out.println(
                                "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        for (VehicleReviewDto r : reviewlist) {
                            System.out.format("%30s%40s%50s%50s%40s", r.getReviewId(), r.getVehicleId(), r.getVehicle_name(), r.getDescription(), r.getRatings());
                            System.out.println("\n");
                        }
                        System.out.println(
                                "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("\n");

                        Review review = new Review();
                        // provide id they want to update
                        System.out.println("Enter your review id where you want to update the rating");
                        int id = sc.nextInt();
                        // provide their new ratings for the vehicle
                        System.out.println("Enter your ratings between '1' to '5'");
                        int ratings = sc.nextInt();
                        review.setRatings(ratings);
                        System.out.println("Enter your Description");
                        sc.nextLine();
                        String Description=sc.nextLine();
                        int status = reviewService.UpdatebyId(id, ratings,Description);
                        if (status == 1) {
                            System.out.println("Your ratings has been updated successfully");
                        } else {
                            System.out.println("Please try again.....");
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidRatingsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    // displays average ratings for each vehicle
                    System.out.println("Displaying Average Review Stats");
                    try {
                        List<ReviewDto> reviewdtolist = reviewService.getAverageRatingsByVehicleId();
                        System.out.println(
                                "---------------------------------------------------------------------------------\n");
                        System.out.format("%30s%30s", "Name of Vehicle", "Average of Vehicle ratings");
                        System.out.println(
                                "\n---------------------------------------------------------------------------------");
                        for (ReviewDto r : reviewdtolist) {
                            System.out.format("%30s%30s", r.getVehicle_name(), r.getAverage());
                            System.out.print("\n");
                        }
                        System.out.println(
                                "---------------------------------------------------------------------------------");
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    // displays reviews for vehicle along with their names--> 2) if there is no review for the particular vehicle it displays empty table.
                    try {
                        System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                        System.out.format("%30s%30s%30s%30s", "Vehicle Id", "Vehiclename", "model", "Dailyrate");
                        System.out.println(
                                "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                        List<Vehicle> vehiclelist = vehicleService.findAll();
                        for (Vehicle v : vehiclelist) {
                            System.out.format("%30s%30s%30s%30s", v.getId(), v.getVehicle_name(), v.getVehicle_model(),
                                    v.getDaily_rate());
                            System.out.print("\n");
                        }
                        System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------------------------------------------");

                        System.out.println("Enter The Vehicle ID which you need to check the review:");
                        List<VehicleReviewDto> vehichlelist = reviewService.getVehicleReviewById(sc.nextInt());
                        System.out.println("\n");
                        System.out.println("Displaying all reviews for the particular Vehicle");
                        System.out.println("\n");
                        System.out.println(
                                "--------------------------------------------------------------------------------------------------------------------------\n");
                        System.out.format("%40s%40s%40s", "Name of Vehicle", "Description", "ratings");
                        System.out.println(
                                "\n--------------------------------------------------------------------------------------------------------------------------");
                        for (VehicleReviewDto v : vehichlelist) {
                            System.out.format("%40s%40s%40s", v.getVehicle_name(), v.getDescription(), v.getRatings());
                            System.out.print("\n");
                        }
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    } catch (ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    // throws a message if the review for a particular vehicle is null
                    catch (NoReviewForParticularVehicleException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }

    }
    public static void Reviews(int customer_id) {
        int user_id = customer_id;
        String loggedInUserId = Integer.toString(user_id);
        String[] menu= {""};
        menu[0] = loggedInUserId;
        main(menu);
    }

}
