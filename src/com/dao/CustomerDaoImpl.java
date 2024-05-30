package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.CustomerDetailsDto;
import com.model.Customer;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public int save(Customer customer) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "INSERT INTO customer(first_name, last_name, phone_number, city, user_id, driving_license) VALUES(?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, customer.getFirst_name());
		pstmt.setString(2, customer.getLast_name());
		pstmt.setString(3, customer.getPhone_number());
		pstmt.setString(4, customer.getCity());
		pstmt.setInt(5, customer.getUser_id());
		pstmt.setString(6, customer.getDriving_license());
		
		int status = pstmt.executeUpdate();
	
		DBConnection.dbClose();		
		return status;
	}

	// finding customer by user id
	@Override
	public Customer getCustomer(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from customer WHERE user_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
		
		Customer c1 = new Customer();
		if(rst.next()) {
			c1.setId(rst.getInt("id"));
			c1.setFirst_name(rst.getString("first_name"));
			c1.setLast_name(rst.getString("last_name"));
			c1.setCity(rst.getString("city"));
			c1.setPhone_number(rst.getString("phone_number"));
			c1.setUser_id(rst.getInt("user_id"));
			c1.setDriving_license(rst.getString("driving_license"));
		}
		
		DBConnection.dbClose();
		return c1;
	}

	@Override
	public List<Customer> getAllCustomers() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from customer" ;
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Customer> list = new ArrayList<>();
		while(rst.next()) {
			int id = rst.getInt("id");
			String first_name = rst.getString("first_name");
			String last_name = rst.getString("last_name");
			String phone_number = rst.getString("phone_number");
			String city = rst.getString("city");
			int user_id = rst.getInt("user_id");
			String driving_license = rst.getString("driving_license");
			
			Customer c1 = new Customer(id, first_name, last_name, phone_number, city, user_id, driving_license);
			list.add(c1);
		}
		
		DBConnection.dbClose();
		
		return list;
	}

	@Override
	public Customer particularCustomer(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from customer WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
		
		Customer c1 = new Customer();
		if(rst.next()) {
			c1.setId(rst.getInt("id"));
			c1.setFirst_name(rst.getString("first_name"));
			c1.setLast_name(rst.getString("last_name"));
			c1.setCity(rst.getString("city"));
			c1.setPhone_number(rst.getString("phone_number"));
			c1.setUser_id(rst.getInt("user_id"));
			c1.setDriving_license(rst.getString("driving_license"));
		}
		
		DBConnection.dbClose();
		return c1;
	}

	@Override
	public int blacklistCustomer(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE customer SET isBlacklisted=1 WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();		
		return status;
	}

	@Override
	public List<CustomerDetailsDto> getDetailsForVendor(int vendorId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select CONCAT(c.first_name,\" \",c.last_name) as customer, c.phone_number, c.driving_license, CONCAT(l.start_date,\" to \",l.last_date) as duration, v.vehicle_name, v.vehicle_model from lease l JOIN customer c ON l.customer_id=c.id JOIN vehicle v ON v.id=l.vehicle_id JOIN vendor vd ON vd.id=v.vendor_id where vd.id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendorId);
		ResultSet rst = pstmt.executeQuery();
		List<CustomerDetailsDto> list = new ArrayList<>();
		while(rst.next()) {
			String customer = rst.getString("customer");
			String phoneNumber = rst.getString("phone_number");
			String drivingLicense = rst.getString("driving_license");
			String duration = rst.getString("duration");
			String vehicleName = rst.getString("vehicle_name");
			
			CustomerDetailsDto details = new CustomerDetailsDto(customer, phoneNumber, drivingLicense, duration, vehicleName);
			list.add(details);
		}
		DBConnection.dbClose();
		return list;
	}

}
