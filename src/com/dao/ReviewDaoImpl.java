package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ReviewDto;
import com.dto.VehicleReviewDto;
import com.exception.NoReviewForParticularVehicleException;
import com.model.Review;
import com.utility.DBConnection;

public class ReviewDaoImpl implements ReviewDao{
	
	@Override
	// customer enters their review
	public int save(Review review) throws SQLException {
		Connection con=DBConnection.dbConnect();
		String sql="INSERT INTO Review (description, ratings, customer_id,vehicle_id) VALUES (?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,review.getDescription());
		pstmt.setInt(2, review.getRatings());
		pstmt.setInt(3,review.getCustomer_id());
		pstmt.setInt(4, review.getVehicle_id());
		int status=pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
		
	}
	//validates whether ratings is within the range else throws exception 
	@Override
	public boolean isRatingsValid(int ratings) {
		// TODO Auto-generated method stub
		return ratings>=1 && ratings<=5;
	}
	//displays all reviews 
	@Override
	public List<VehicleReviewDto> findAll() throws SQLException {
		Connection con=DBConnection.dbConnect();
		String sql="select v.id, v.vehicle_name, r.description, r.ratings from review r JOIN vehicle v ON r.vehicle_id=v.id";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<VehicleReviewDto> list = new ArrayList<>();
		while(rst.next() == true) {
			int vehicleId = rst.getInt("id");
			String vehicleName = rst.getString("vehicle_name");
			String description =rst.getString("description");
			int ratings=rst.getInt("ratings");
			VehicleReviewDto review = new VehicleReviewDto(vehicleId, vehicleName, description, ratings);
			list.add(review);
		}
		DBConnection.dbClose();
		return list;
	}
	//find whether the given id is present in the table or not and returns true/false if there is a row in the result set 
	@Override
	public boolean findOne(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql="select id from review where id=?";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rst  = pstmt.executeQuery();
		boolean status = rst.next(); //true / false
		DBConnection.dbClose();
		return status;
	}
	// after finding the review by provided by id customer can delete the review by id
	@Override
	public void DeleteById(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql="delete from review where id =?";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		
	}
	//joins vehicle and review to give vehicle and average ratings by using group by on vehicle name
	@Override
	public List<ReviewDto> getAverageRatingsByVehicleId() throws SQLException {
		Connection con=DBConnection.dbConnect();
		String sql="select v.vehicle_name,avg(r.ratings) as Averageratings From "
				+ "vehicle v JOIN review r ON v.id=r.vehicle_id "
				+ "Group by v.vehicle_name;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<ReviewDto> list = new ArrayList<>();
		while(rst.next() == true) {
			String name=rst.getString("vehicle_name");
			double Average=rst.getInt("Averageratings");
			ReviewDto reviewdto = new ReviewDto(name,Average); 
			list.add(reviewdto);
		}
		DBConnection.dbClose();
		return list;
	}
	//displays vehicle and their reviews 
	@Override
	public List<VehicleReviewDto> getVehicleReviewsById(int id) throws SQLException, NoReviewForParticularVehicleException {
		Connection con=DBConnection.dbConnect();
		String sql="select v.id, v.vehicle_name,r.description,r.ratings From "
				+ "vehicle v  JOIN review r ON v.id=r.vehicle_id "
				+ "where v.id=?; ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rst = pstmt.executeQuery();
		List<VehicleReviewDto> list = new ArrayList<>();
		if (rst.next()==false) {
			throw new NoReviewForParticularVehicleException("There is no review for particular vehicle");
		}
			int vehicleId = rst.getInt("id");
			String name=rst.getString("vehicle_name");
			String description=rst.getString("description");
			int ratings=rst.getInt("ratings");
			VehicleReviewDto vehiclereviewdto = new VehicleReviewDto(vehicleId, name,description,ratings); 
			list.add(vehiclereviewdto);
		
		DBConnection.dbClose();
		return list;
	}
	// if the customer wants to update their ratings they can provide id and ratings they want to update
	@Override
	public int UpdateById(int id,int ratings,String Description) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DBConnection.dbConnect();
		String sql = "update review set ratings=? , description=? where id=?";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
		pstmt.setInt(1, ratings);
		pstmt.setString(2, Description);
		pstmt.setInt(3, id);
		int status=pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}
	@Override
	public List<VehicleReviewDto> findReviewByCustomerId(int customer_id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql="select r.id as review_id, v.id as vehicle_id, v.vehicle_name, r.description, r.ratings from review r JOIN vehicle v ON r.vehicle_id=v.id WHERE customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,customer_id);
		ResultSet rst  = pstmt.executeQuery();
		List<VehicleReviewDto> list = new ArrayList<>();
		while(rst.next()) {
			int reviewId = rst.getInt("review_id");
			int vehicleId = rst.getInt("vehicle_id");
			String vehicleName = rst.getString("vehicle_name");
			String description = rst.getString("description");
			int ratings = rst.getInt("ratings");
			
			VehicleReviewDto review = new VehicleReviewDto(reviewId, vehicleId, vehicleName, description, ratings);
			list.add(review);
		}
		DBConnection.dbClose();
		return list;
	}

}
