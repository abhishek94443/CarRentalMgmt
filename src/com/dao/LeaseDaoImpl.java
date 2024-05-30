package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.BookingDto;
import com.dto.CustomerLeaseDto;
import com.exception.CarNotFoundException;
import com.model.Lease;
import com.utility.DBConnection;

public class LeaseDaoImpl implements LeaseDao{

	@Override
	public int insertIntoLease(Lease lease) throws SQLException, CarNotFoundException {
		Connection con = DBConnection.dbConnect();
		String sql = "INSERT INTO lease(customer_id, vehicle_id, start_date, last_date, status, type) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, lease.getCustomer_id());
		pstmt.setInt(2, lease.getVehicle_id());
		pstmt.setString(3, lease.getStart_date());
		pstmt.setString(4, lease.getEnd_date());
		pstmt.setString(5, lease.getStatus());
		pstmt.setString(6, lease.getType());
		
		int status = pstmt.executeUpdate();
		
		if(status == 0) {
			throw new SQLException("Invalid details, please try again");
		}
	
		DBConnection.dbClose();		
		return status;
	}

	@Override
	public List<BookingDto> getAllLeases() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select CONCAT(c.first_name,\" \",c.last_name) as customer_name, v.vehicle_name, l.* from lease l JOIN customer c ON l.customer_id=c.id JOIN vehicle v ON l.vehicle_id=v.id;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<BookingDto> list = new ArrayList<>();
		while(rst.next()) {
			String customerName = rst.getString("customer_name");
			String vehicleName = rst.getString("vehicle_name");
			int customerId = rst.getInt("customer_id");
			int vehicleId = rst.getInt("vehicle_id");
			int id = rst.getInt("id");
			String startDate = rst.getString("start_date");
			String endDate = rst.getString("last_date");
			String status = rst.getString("status");
			String type = rst.getString("type");
			
			BookingDto b1 = new BookingDto(customerName, vehicleName, customerId, vehicleId, id, startDate, endDate, status, type);
			list.add(b1);
		}
		
		DBConnection.dbClose();
		
		return list;
	}

	@Override
	public List<CustomerLeaseDto> getMyUpcomingDeals(int vendorId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select CONCAT(c.first_name,\" \",c.last_name) as customer_name, l.start_date as deal_date, v.vehicle_name, v.id as vehicle_id, l.id as deal_id from lease l JOIN vehicle v ON v.id=l.vehicle_id JOIN customer c ON c.id=l.customer_id WHERE l.status=\"pending\" AND v.vendor_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendorId);
		ResultSet rst = pstmt.executeQuery();
		List<CustomerLeaseDto> list = new ArrayList<>();
		while(rst.next()) {
			String customer = rst.getString("customer_name");
			String date = rst.getString("deal_date");
			String vehicle = rst.getString("vehicle_name");
			int vehicleId = rst.getInt("vehicle_id");
			int dealId = rst.getInt("deal_id");
			CustomerLeaseDto cust = new CustomerLeaseDto(customer, date, vehicle, vehicleId, dealId);
			list.add(cust);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int deliverVehicle(int dealId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE lease SET status='ongoing' WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dealId);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;
	}

	@Override
	public int leaseCompleted(int dealId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE lease SET status='completed' WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dealId);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();
		return status;		
	}

}
