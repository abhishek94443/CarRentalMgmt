package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.ParticularVendorDto;
import com.dto.ReadyToReturnDto;
import com.dto.VehicleReviewCustomerDto;
import com.dto.VendorProfitDto;
import com.model.Vendor;
import com.utility.DBConnection;

public class VendorDaoImpl implements VendorDao {

	@Override
	public int save(Vendor vendor) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "INSERT INTO vendor(name, identity_proof, phone_number, user_id) VALUES(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, vendor.getName());
		pstmt.setString(2, vendor.getIdentity_proof());
		pstmt.setString(3, vendor.getPhone_number());
		pstmt.setInt(4, vendor.getUser_id());

		int status = pstmt.executeUpdate();
	
		DBConnection.dbClose();		
		return status;
	}

	@Override
	public List<Vendor> getAllVendors() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from vendor" ;
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Vendor> list = new ArrayList<>();
		while(rst.next()) {
			int id = rst.getInt("id");
			String name = rst.getString("name");
			String identity_proof = rst.getString("identity_proof");
			String phone_number = rst.getString("phone_number");
			double commission = rst.getDouble("commission");
			int user_id = rst.getInt("user_id");
			
			Vendor v1 = new Vendor(id, name, identity_proof, phone_number, user_id, commission);
			list.add(v1);
		}
		
		DBConnection.dbClose();
		
		return list;
	}

	@Override
	public ParticularVendorDto getParticularVendor(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
//		String sql = "select vd.*, COUNT(v.vendor_id) as no_of_cars from vendor vd JOIN vehicle v ON vd.id=v.vendor_id group by v.vendor_id HAVING vd.id=?";
		String sql = "SELECT vd.id, vd.name, vd.identity_proof, vd.phone_number, vd.commission, vd.user_id, COUNT(v.vendor_id) as no_of_cars "
				+ "FROM vendor vd "
				+ "JOIN vehicle v ON vd.id = v.vendor_id "
				+ "WHERE vd.id = ? "
				+ "GROUP BY  vd.id, vd.name, vd.identity_proof, vd.phone_number, vd.commission, vd.user_id;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
		
		if(rst.next()) {			
			 int vendorId = rst.getInt("id");
			 String name = rst.getString("name");
			 String identityProof = rst.getString("identity_proof");
			 String phoneNumber = rst.getString("phone_number");
			 double commission = rst.getDouble("commission");
			 int userId = rst.getInt("user_id");
			 int numberOfCars = rst.getInt("no_of_cars");
			 
			 ParticularVendorDto v1 = new ParticularVendorDto(vendorId, name, identityProof, phoneNumber, commission, numberOfCars, userId);
			 return v1;
			 
		} else {
			DBConnection.dbClose();
			return null;
		}		
	}

	@Override
	public int blacklistVendor(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE vendor SET is_blacklist=1 WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();		
		return status;
	}

	@Override
	public int changeCommission(int id, double c) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "UPDATE vendor SET commission=? WHERE id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setDouble(1, c);
		pstmt.setInt(2, id);
		int status = pstmt.executeUpdate();
		DBConnection.dbClose();		
		return status;
	}

	@Override
	public List<VendorProfitDto> getProfits() throws SQLException {
		Connection con = DBConnection.dbConnect();
//		String sql = "select vd.name, SUM(v.daily_rate)*((100-vd.commission)/100) as profit_per_day, SUM(v.daily_rate)*((100-vd.commission)/100)*30 as profit_per_month from vendor vd JOIN vehicle v ON v.vendor_id=vd.id group by v.vendor_id;";
		String sql = "SELECT vd.id, "
				+ "       vd.name, "
				+ "       SUM(v.daily_rate) * ((100 - vd.commission) / 100) AS profit_per_day, "
				+ "       SUM(v.daily_rate) * ((100 - vd.commission) / 100) * 30 AS profit_per_month "
				+ "FROM vendor vd \n"
				+ "JOIN vehicle v ON v.vendor_id = vd.id "
				+ "GROUP BY vd.id, vd.name, vd.commission;";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<VendorProfitDto> list = new ArrayList<>();
		while(rst.next()) {
			String name = rst.getString("name");
			double profitPerDay = rst.getDouble("profit_per_day");
			double profitPerMonth = rst.getDouble("profit_per_month");
			
			VendorProfitDto vpd = new VendorProfitDto(name, profitPerDay, profitPerMonth);
			list.add(vpd);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public Vendor getVendorByUserId(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "SELECT * from vendor WHERE user_id="+id;
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		
		Vendor v1 = new Vendor();
		if(rst.next()) {
			v1.setId(rst.getInt("id"));
			v1.setName(rst.getString("name"));
			v1.setIdentity_proof(rst.getString("identity_proof"));
			v1.setPhone_number(rst.getString("phone_number"));
			v1.setUser_id(rst.getInt("user_id"));
			v1.setCommission(rst.getDouble("commission"));
		}
		
		DBConnection.dbClose();
		return v1;
	}

	@Override
	public List<ReadyToReturnDto> getBackVehicle(int vendorId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select l.id as deal_id, c.id as customer_id, CONCAT(c.first_name,\" \",c.last_name) as customer_name, c.phone_number, v.vehicle_name, v.id as vehicle_id, l.last_date from lease l JOIN vehicle v ON l.vehicle_id=v.id JOIN customer c ON l.customer_id=c.id WHERE status=\"ready to return\" AND v.vendor_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendorId);
		ResultSet rst = pstmt.executeQuery();
		
		List<ReadyToReturnDto> list = new ArrayList<>();
		
		while(rst.next()) {
			int dealId = rst.getInt("deal_id");
			int customerId = rst.getInt("customer_id");
			String customerName = rst.getString("customer_name");
			String phoneNumber = rst.getString("phone_number");
			String vehicleName = rst.getString("vehicle_name");
			int vehicleId = rst.getInt("vehicle_id");
			String lastDate = rst.getString("last_date");
			
			ReadyToReturnDto details = new ReadyToReturnDto(dealId, customerId, customerName, phoneNumber, vehicleName, vehicleId, lastDate);
			list.add(details);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<VehicleReviewCustomerDto> getReviewsOnVehicles(int vendorId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select v.vehicle_name, r.description, r.ratings, CONCAT(c.first_name,\" \",c.last_name) as customer from review r JOIN vehicle v ON v.id=r.vehicle_id JOIN customer c ON c.id=r.customer_id WHERE v.vendor_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, vendorId);
		ResultSet rst = pstmt.executeQuery();
		
		List<VehicleReviewCustomerDto> list = new ArrayList<>();
		while(rst.next()) {
			String vehicleName = rst.getString("vehicle_name");
			String description = rst.getString("description");
			String customer = rst.getString("customer");
			int ratings = rst.getInt("ratings");
			
			VehicleReviewCustomerDto review = new VehicleReviewCustomerDto(vehicleName, customer, description, ratings);
			list.add(review);
		}
		DBConnection.dbClose();
		return list;
	}
	
}
