package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.ReviewDto;
import com.dto.VehicleReviewDto;
import com.exception.NoReviewForParticularVehicleException;
import com.exception.ResourceNotFoundException;
import com.model.Review;

public interface ReviewDao {
	int save(Review review) throws SQLException;

	boolean isRatingsValid(int ratings);

	boolean findOne(int id) throws SQLException, ResourceNotFoundException;

	void DeleteById(int id) throws SQLException, ResourceNotFoundException;

	List<VehicleReviewDto> findAll() throws SQLException;

	List<ReviewDto> getAverageRatingsByVehicleId() throws SQLException;

	List<VehicleReviewDto> getVehicleReviewsById(int id) throws SQLException, ResourceNotFoundException, NoReviewForParticularVehicleException;

	int UpdateById(int id, int ratings,String Description) throws SQLException;

	List<VehicleReviewDto> findReviewByCustomerId(int customer_id) throws SQLException;
}
