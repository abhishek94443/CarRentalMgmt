package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.BookingDto;
import com.dto.CustomerLeaseDto;
import com.exception.CarNotFoundException;
import com.model.Lease;

public interface LeaseDao {
	public int insertIntoLease(Lease lease) throws SQLException, CarNotFoundException;
	public List<BookingDto> getAllLeases() throws SQLException;
	public List<CustomerLeaseDto> getMyUpcomingDeals(int vendorId) throws SQLException;
	public int deliverVehicle(int dealId) throws SQLException;
	public int leaseCompleted(int dealId) throws SQLException;
}
