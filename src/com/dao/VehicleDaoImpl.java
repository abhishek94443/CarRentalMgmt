package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dto.VehicleDto;
import com.dto.VehicleLeaseDto;
import com.exception.CarNotFoundException;
import com.exception.ResourceNotFoundException;
import com.model.Vehicle;
import com.utility.DBConnection;

public class VehicleDaoImpl implements VehicleDao {

	@Override
	public List<Vehicle> getAll() throws SQLException { // all the vehicles
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from vehicle";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Vehicle> list = new ArrayList<>();
		while(rst.next()) {
			int id = rst.getInt("id");
			String vehicle_name = rst.getString("vehicle_name");
			String vehicle_model = rst.getString("vehicle_model");
			String vehicle_year = rst.getString("vehicle_year");
			float daily_rate = rst.getFloat("daily_rate");
			int availability_status = rst.getInt("availability_status");			
			int passenger_capacity = rst.getInt("passenger_capacity");
			String engine_capacity = rst.getString("engine_capacity");
			int vendor_id = rst.getInt("vendor_id");
			
			Vehicle v1 = new Vehicle(id, vehicle_name, vehicle_model, vehicle_year, daily_rate, availability_status, passenger_capacity, engine_capacity, vendor_id);
			list.add(v1);
		}
		
		DBConnection.dbClose();
		
		return list;
	}

	@Override
	public List<Vehicle> getAllAvailable() throws SQLException { // getting available vehicles to rent
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from vehicle WHERE availability_status=1 AND vendor_active=1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Vehicle> list = new ArrayList<>();
		while(rst.next()) {
			int id = rst.getInt("id");
			String vehicle_name = rst.getString("vehicle_name");
			String vehicle_model = rst.getString("vehicle_model");
			String vehicle_year = rst.getString("vehicle_year");
			float daily_rate = rst.getFloat("daily_rate");
			int availability_status = rst.getInt("availability_status");			
			int passenger_capacity = rst.getInt("passenger_capacity");
			String engine_capacity = rst.getString("engine_capacity");
			int vendor_id = rst.getInt("vendor_id");
			
			Vehicle v1 = new Vehicle(id, vehicle_name, vehicle_model, vehicle_year, daily_rate, availability_status, passenger_capacity, engine_capacity, vendor_id);
			list.add(v1);
		}
		
		DBConnection.dbClose();
		
		return list;
	}

	@Override
	public int deleteVehicle(int id) throws SQLException { // soft delete of a vehicle
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE vehicle SET vendor_active=0 WHERE vendor_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();		
		return status;
	}

	@Override
	public int changeAvailabilityStatus(int vehicleId, int value) throws SQLException{ // change availability of a vehicle either 1 or 0
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE vehicle SET availability_status=? WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, value);
		pstmt.setInt(2, vehicleId);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();		
		return status;
	}
	
	// ----------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public int save(Vehicle vehicle) throws SQLException { // Adding into vehicle table (insertion)
		// insert artist record in DB
				Connection con = DBConnection.dbConnect();
				String sql="INSERT INTO Vehicle (vehicle_name , vehicle_model, vehicle_year, daily_rate, availability_status, passenger_capacity, engine_capacity, vendor_id) VALUES (?,?,?,?,?,?,?,?)";
				//prepare the statement 
				PreparedStatement pstmt = con.prepareStatement(sql);
				//attach the data
				pstmt.setString(1, vehicle.getVehicle_name());
				pstmt.setString(2, vehicle.getVehicle_model());
				pstmt.setString(3, vehicle.getVehicle_year());
				pstmt.setDouble(4, vehicle.getDaily_rate());
				pstmt.setInt(5, vehicle.getAvailability_status());
				pstmt.setInt(6, vehicle.getPassenger_capacity());
				pstmt.setString(7, vehicle.getEngine_capacity());
				pstmt.setInt(8, vehicle.getVendor_id());
				//execute the query 
				int status = pstmt.executeUpdate(); //1: if all good., 0 - if op fails 
				DBConnection.dbClose();
				return status;
	}

	@Override
	public Boolean findOne(int id) throws SQLException { // Find vehicle by id
		Connection con = DBConnection.dbConnect();
		String sql="select id from vehicle where id=?";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst  = pstmt.executeQuery();
		boolean status = rst.next(); //true / false
		DBConnection.dbClose();
		return status;
	}

	@Override
	public void deleteById(int id) throws SQLException, ResourceNotFoundException { // hard delete by ID
		Connection con = DBConnection.dbConnect();
		String sql="delete from vehicle where id =?";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
	}
	
	@Override
	public List<Vehicle> findAll() throws SQLException { // All the vehicles
		Connection con = DBConnection.dbConnect();
		String sql="select * from vehicle";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Vehicle> list = new ArrayList<>();
		while(rst.next() == true) {
			
			int id  = rst.getInt("id");
			String vehicleName= rst.getString("vehicle_name");
			String vehicleModel= rst.getString("vehicle_model");;
			String vehicleYear= rst.getString("vehicle_year");;
			float dailyRate= rst.getFloat("daily_rate");
			int availabilityStatus= rst.getInt("availability_status");
			int passengerCapacity= rst.getInt("passenger_capacity");
			String engineCapacity= rst.getString("engine_capacity");;
			int vendorId = rst.getInt("vendor_id");;
			
			Vehicle vehicle = new Vehicle(id, vehicleName, vehicleModel, vehicleYear, dailyRate, availabilityStatus, passengerCapacity, engineCapacity, vendorId);
			
			list.add(vehicle);
		}
		DBConnection.dbClose();		
		return list;
	}
	
	@Override
	public VehicleDto getAvgDailyRate(int vendorId) throws SQLException { // As a vendor, average daily rate of my vehicles
	    Connection con = DBConnection.dbConnect();
	    String sql = "SELECT v.vendor_id, AVG(v.daily_rate) AS average_daily_rate " +
	                 "FROM car_rental.vehicle v " +
	                 "WHERE v.vendor_id = ? " +
	                 "GROUP BY v.vendor_id";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, vendorId);

	    ResultSet rst = pstmt.executeQuery();
	    VehicleDto vehicleDto = new VehicleDto(); 

	    if (rst.next()) {
	        double averageDailyRate = rst.getDouble("average_daily_rate");
	        vehicleDto.setValue(averageDailyRate);
	    }

	    DBConnection.dbClose();     
	    return vehicleDto;
	}

	@Override
	public List<Vehicle> findAllfromVendor(int vendorId) throws SQLException { // find all vehicles from a particular vendor.
		Connection con = DBConnection.dbConnect();
		String sql="select * from vehicle where vendor_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendorId);
		ResultSet rst = pstmt.executeQuery();
		List<Vehicle> list = new ArrayList<>();
		while(rst.next() == true) {
			
			int id  = rst.getInt("id");
			String vehicleName= rst.getString("vehicle_name");
			String vehicleModel= rst.getString("vehicle_model");
			String vehicleYear= rst.getString("vehicle_year");
			float dailyRate= rst.getFloat("daily_rate");
			int availabilityStatus= rst.getInt("availability_status");
			int passengerCapacity= rst.getInt("passenger_capacity");
			String engineCapacity= rst.getString("engine_capacity");
			Vehicle vehicle = new Vehicle(id, vehicleName , vehicleModel, vehicleYear, dailyRate, availabilityStatus, passengerCapacity, engineCapacity, vendorId);
			list.add(vehicle);
		}
		DBConnection.dbClose();		
		return list;
	}

	@Override
	public List<VehicleLeaseDto> getMyLeasedCars(int customer_id) throws SQLException { // as a customer getting the vehicles where the last date has passed
		Connection con = DBConnection.dbConnect();
		String sql = "select l.id, v.vehicle_name from vehicle v JOIN lease l ON v.id=l.vehicle_id WHERE l.customer_id=? AND last_date>=? AND l.status='ongoing'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		String date = LocalDate.now().toString();
		pstmt.setInt(1, customer_id);
		pstmt.setString(2, date);
		ResultSet rst = pstmt.executeQuery();
		List<VehicleLeaseDto> list = new ArrayList<>();
		while(rst.next()) {
			int id = rst.getInt("id");
			String vehicleName = rst.getString("vehicle_name");
			VehicleLeaseDto vehicleleasedto = new VehicleLeaseDto(id, vehicleName);
			list.add(vehicleleasedto);
		}
		
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int returnVehicle(int returnId) throws SQLException { // returnVehicle basically means when customer returns vehicle hence deal=completed therefore changing lease status. 
		Connection con = DBConnection.dbConnect();
		String sql="UPDATE lease SET status='ready to return' WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, returnId);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}
	
	@Override
	public void updateDailyRate(int vehicleID, double daily_rate) throws SQLException, CarNotFoundException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE vehicle SET daily_rate=? WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, daily_rate);
		pstmt.setInt(2, vehicleID);
		pstmt.executeUpdate();
		DBConnection.dbClose();	
	}

	@Override
	public List<VehicleDto> getMostLeasedVehicle(int vendorId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql="SELECT CONCAT(v.id, ' - ', v.vehicle_name) AS vehicle_info, COUNT(l.id) AS num_leases "
				+ "FROM vehicle v "
				+ "JOIN lease l ON v.id = l.vehicle_id "
				+ "WHERE v.vendor_id = ? "
				+ "GROUP BY v.id, v.vehicle_name "
				+ "ORDER BY num_leases DESC "
				+ "LIMIT 3;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendorId);
		ResultSet rst = pstmt.executeQuery();
		List<VehicleDto> list = new ArrayList<>();
		while (rst.next()) {
			String vehicle_info = rst.getString("vehicle_info");
	        double num_leases = rst.getDouble("num_leases");
	        VehicleDto vehicle = new VehicleDto(vehicle_info, num_leases);
			list.add(vehicle);
	    }
			
		DBConnection.dbClose();		
		return list;
	}

	@Override
	public Vehicle getVehicleById(int id) throws SQLException{
		Connection con = DBConnection.dbConnect();
	    String sql = "select * from vehicle where id=?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, id);

	    ResultSet rst = pstmt.executeQuery();
	    Vehicle vehicle = new Vehicle(); 

	    if (rst.next()) {
	    	vehicle.setId(rst.getInt("id"));
	        vehicle.setVehicle_name(rst.getString("vehicle_name"));
	        vehicle.setVehicle_model(rst.getString("vehicle_model"));
	        vehicle.setVehicle_year(rst.getString("vehicle_year"));
	        vehicle.setDaily_rate(rst.getFloat("daily_rate"));
	        vehicle.setAvailability_status(rst.getInt("availability_status"));
	        vehicle.setPassenger_capacity(rst.getInt("passenger_capacity"));
	        vehicle.setEngine_capacity(rst.getString("engine_capacity"));
	        vehicle.setVendor_id(rst.getInt("vendor_id"));   
	}

 DBConnection.dbClose();     
	    return vehicle;
}

	@Override
	public int insertWithID(Vehicle newVehicle) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql="INSERT INTO Vehicle (id ,vehicle_name , vehicle_model, vehicle_year, daily_rate, availability_status, passenger_capacity, engine_capacity, vendor_id) VALUES (?,?,?,?,?,?,?,?,?)";
		//prepare the statement 
		PreparedStatement pstmt = con.prepareStatement(sql);
		//attach the data
		pstmt.setInt(1, newVehicle.getId());
		pstmt.setString(2, newVehicle.getVehicle_name());
		pstmt.setString(3, newVehicle.getVehicle_model());
		pstmt.setString(4, newVehicle.getVehicle_year());
		pstmt.setDouble(5, newVehicle.getDaily_rate());
		pstmt.setInt(6, newVehicle.getAvailability_status());
		pstmt.setInt(7, newVehicle.getPassenger_capacity());
		pstmt.setString(8, newVehicle.getEngine_capacity());
		pstmt.setInt(9, newVehicle.getVendor_id());
		//execute the query 
		int status = pstmt.executeUpdate(); 
		DBConnection.dbClose();
		return status;
	}


}
