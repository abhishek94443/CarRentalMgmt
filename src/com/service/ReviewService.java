package com.service;

import java.sql.SQLException;
import java.util.List;

import com.exception.InvalidRatingsException;
import com.exception.NoReviewForParticularVehicleException;
import com.exception.ResourceNotFoundException;
import com.model.Review;
import com.dao.ReviewDao;
import com.dao.ReviewDaoImpl;
import com.dao.VehicleDao;
import com.dao.VehicleDaoImpl;
import com.dto.ReviewDto;
import com.dto.VehicleReviewDto;



public class ReviewService {
	ReviewDao reviewDao = new ReviewDaoImpl();
	VehicleDao vehicleDao = new VehicleDaoImpl();
	Review review = new Review();
	
	public int save(Review review) throws SQLException, ResourceNotFoundException, InvalidRatingsException {
		// VehicleIdValidation
		boolean isVehicleIdValid = vehicleDao.findOne(review.getVehicle_id());
		if (!isVehicleIdValid)
			throw new ResourceNotFoundException("Vehicle ID invalid");
		//Ratings Should be between 1 to 5 else throw the exception 
		boolean isRatingsValid=reviewDao.isRatingsValid(review.getRatings());
		if(!isRatingsValid) {
			throw new InvalidRatingsException("Your rating Should be between '1' to '5'");
		}
		return reviewDao.save(review);
	}
	
	public List<VehicleReviewDto> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return reviewDao.findAll();
	}

	public void DeleteById(int id) throws SQLException, ResourceNotFoundException {
		Boolean isReviewIdValid = reviewDao.findOne(id);
		if (!isReviewIdValid)
			throw new ResourceNotFoundException("Entered review id is not valid!");
		reviewDao.DeleteById(id);
	}
	
	public int UpdatebyId(int id,int ratings,String Description) throws SQLException, ResourceNotFoundException ,InvalidRatingsException{
		Boolean isReviewIdValid = reviewDao.findOne(review.getId());
		//review id validation
		if (!isReviewIdValid)
			throw new ResourceNotFoundException("Entered review id is not valid!");
		boolean isRatingsValid=reviewDao.isRatingsValid(ratings);
		//ratings validation
		if(!isRatingsValid) {
			throw new InvalidRatingsException("Your rating Should be between '1' to '5'");
		}
		return reviewDao.UpdateById(id,ratings,Description );
	}

	public List<ReviewDto> getAverageRatingsByVehicleId() throws SQLException {
		// TODO Auto-generated method stub
		return reviewDao.getAverageRatingsByVehicleId();
	}

	public List<VehicleReviewDto> getVehicleReviewById(int id) throws SQLException, ResourceNotFoundException, NoReviewForParticularVehicleException {
		//vehicle id validation
		boolean isVehicleIdValid = vehicleDao.findOne(id);
		if (!isVehicleIdValid)
			throw new ResourceNotFoundException("Vehicle ID invalid");
		return reviewDao.getVehicleReviewsById(id);
	}

	public List<VehicleReviewDto> findReviewByCustomerId(int customer_id) throws SQLException {
		return reviewDao.findReviewByCustomerId(customer_id);
	}

}
