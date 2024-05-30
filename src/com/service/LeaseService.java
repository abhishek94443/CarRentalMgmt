package com.service;

import java.sql.SQLException;
import java.util.List;

import com.model.Lease;
import com.dao.LeaseDao;
import com.dao.LeaseDaoImpl;
import com.dto.BookingDto;
import com.dto.CustomerLeaseDto;
import com.exception.CarNotFoundException;

public class LeaseService {
	
	LeaseDao dao = new LeaseDaoImpl();
	
	public int bookVehicle(Lease lease) throws SQLException, CarNotFoundException {
		return dao.insertIntoLease(lease);
	}
	
	public List<BookingDto> getAllBookings() throws SQLException {
		return dao.getAllLeases();
	}

	public List<CustomerLeaseDto> getMyUpcomingDeals(int vendorId) throws SQLException {
		return dao.getMyUpcomingDeals(vendorId);
	}

	public int deliverVehicle(int dealId) throws SQLException{
		return dao.deliverVehicle(dealId);
	}

	public int leaseCompleted(int dealId) throws SQLException {
		return dao.leaseCompleted(dealId);
	}

}
