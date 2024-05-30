package com.service;

import java.sql.SQLException;
import java.util.List;

import com.model.Vehicle;
import com.dao.VehicleDao;
import com.dao.VehicleDaoImpl;
import com.dto.VehicleDto;
import com.dto.VehicleLeaseDto;
import com.exception.CarNotFoundException;
import com.exception.ResourceNotFoundException;

public class VehicleService {
	
	VehicleDao dao = new VehicleDaoImpl();

	public List<Vehicle> getAllCars() throws SQLException {
		return dao.getAll();
	}

	public List<Vehicle> getAvailableCars() throws SQLException {
		return dao.getAllAvailable();
	}

	public int deleteVehicle(int id) throws SQLException {
		return dao.deleteVehicle(id);
	}

	public int changeAvailabilityStatus(int vehicleId, int value) throws SQLException{
		return dao.changeAvailabilityStatus(vehicleId, value);
	}
	
	/* ----------------------------------------------------------------------------------------- */
	
	public int insert(Vehicle vehicle)  throws SQLException{
		return dao.save(vehicle);
	}
	
	public void deleteByid(int id) throws SQLException, ResourceNotFoundException {
		boolean isIdValid = dao.findOne(id);
		if(!isIdValid)
			throw new ResourceNotFoundException("Id given is Invalid!!");
		 
		dao.deleteById(id);
	}
	
	public List<Vehicle> findAll() throws SQLException{
		return dao.findAll();
	}
	
	public List<Vehicle> findAllfromVendor(int vendorId) throws SQLException{
		return dao.findAllfromVendor(vendorId);
	}
	
	public VehicleDto getAvgDailyRate(int vendorId) throws SQLException {
		return dao.getAvgDailyRate(vendorId);
	}

	// -------------------------------------------------------------------------------------------------------
	public List<VehicleLeaseDto> getMyLeasedCars(int customer_id) throws SQLException {
		return dao.getMyLeasedCars(customer_id);
	}

	public int returnVehicle(int returnVehicleId) throws SQLException {
		return dao.returnVehicle(returnVehicleId);
	}
	// ------------------------------------------------------------------------------------------------------------
	
	public void updateDailyRate(int vehicleID, double daily_rate) throws SQLException, CarNotFoundException{
		boolean isIdValid = dao.findOne(vehicleID);
		if(!isIdValid)
			throw new CarNotFoundException("VehicleId of the Car given is Invalid!!");
		dao.updateDailyRate(vehicleID,daily_rate);
		
	}

	public List<VehicleDto> getMostLeasedVehicle(int vendorId)  throws SQLException{
		return dao.getMostLeasedVehicle(vendorId);
	}

	public Vehicle getVehicleById(int id) throws SQLException{
		return dao.getVehicleById(id);
	}

	public int insertWithID(Vehicle newVehicle) throws SQLException{
		return dao.insertWithID(newVehicle);
	
	}
}
