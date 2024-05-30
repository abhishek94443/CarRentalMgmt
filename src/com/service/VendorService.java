package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.VendorDao;
import com.dao.VendorDaoImpl;
import com.dto.ParticularVendorDto;
import com.dto.ReadyToReturnDto;
import com.dto.VehicleReviewCustomerDto;
import com.dto.VendorProfitDto;
import com.model.Vendor;

public class VendorService {
	VendorDao dao = new VendorDaoImpl();
	
	public int insert(Vendor vendor) throws SQLException {
		return dao.save(vendor);
	}
	
	public List<Vendor> getAllVendors() throws SQLException {
		return dao.getAllVendors();
	}
	
	public ParticularVendorDto getVendorById(int id) throws SQLException {
		return dao.getParticularVendor(id);
	}
	
	public int blockVendor(int id) throws SQLException {
		return dao.blacklistVendor(id);		
	}

	public int changeCommission(int id, double c) throws SQLException {
		return dao.changeCommission(id, c);
	}

	public List<VendorProfitDto> getProfits() throws SQLException {
		return dao.getProfits();
	}

	public Vendor getVendor(int id) throws SQLException {
		return dao.getVendorByUserId(id);
	}

	public List<ReadyToReturnDto> getBackVehicle(int vendorId) throws SQLException {
		return dao.getBackVehicle(vendorId);
	}

	public List<VehicleReviewCustomerDto> getReviewsOnVehicles(int vendorId) throws SQLException{
		return dao.getReviewsOnVehicles(vendorId);
	}
}
