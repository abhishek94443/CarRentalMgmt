package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.CustomerHistoryDto;
import com.dto.DamageReportDto;
import com.exception.NewCustomerException;
import com.exception.ResourceNotFoundException;
import com.exception.WrongInformationException;
import com.model.CustomerHistory;
import com.model.Lease;
import com.utility.DBConnection;

public class CustomerHistoryDaoImpl implements CustomerHistoryDao{

	@Override
	public List<CustomerHistoryDto> findAll(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql="select v.id, v.vehicle_name, c.final_amount, c.discount, any_damage_reported from customer_history c JOIN vehicle v ON v.id=c.vehicle_id WHERE c.customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rst = pstmt.executeQuery();
		List<CustomerHistoryDto> list = new ArrayList<>();
		while(rst.next()) {
			int vehicleId = rst.getInt("v.id");
			String vehicleName = rst.getString("vehicle_name");
			Double finalAmount = rst.getDouble("final_amount");
			Double discount = rst.getDouble("discount");
			String damageReported = rst.getString("any_damage_reported");
			
			CustomerHistoryDto hist = new CustomerHistoryDto(vehicleId, vehicleName, finalAmount, discount, damageReported);
			list.add(hist);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<Lease> GetOngoingDeals(int id) throws SQLException {
		Connection con=DBConnection.dbConnect();
		String sql="select * from lease where status='ongoing' AND customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
		List<Lease> list = new ArrayList<>();
		while(rst.next() == true) {
//			int Id=rst.getInt("id");
			String startDate=rst.getString("start_date");
			String lastDate=rst.getString("last_date");
			String status=rst.getString("status");
			String type=rst.getString("type");
			int customerhistoryid=rst.getInt("customer_id");
			int vehicleId=rst.getInt("vehicle_id");
			Lease lease = new Lease(customerhistoryid, vehicleId, startDate, lastDate, status, type); 
			list.add(lease);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<Lease> GetPendingDeals(int id) throws SQLException {
		Connection con=DBConnection.dbConnect();
		String sql="select * from lease where status='pending' AND customer_id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rst = pstmt.executeQuery();
		List<Lease> list = new ArrayList<>();
		while(rst.next() == true) {
			String startDate=rst.getString("start_date");
			String lastDate=rst.getString("last_date");
			String status=rst.getString("status");
			String type=rst.getString("type");
			int customerhistoryid=rst.getInt("customer_id");
			int vehicleId=rst.getInt("vehicle_id");
			Lease lease = new Lease(customerhistoryid, vehicleId, startDate, lastDate, status, type); 
			list.add(lease);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int insertIntoHistory(CustomerHistory c) throws SQLException, WrongInformationException {
		Connection con=DBConnection.dbConnect();
		String sql = "INSERT INTO customer_history (customer_id, discount, vehicle_id, late_return_fee, start_mileage, end_mileage, any_damage_reported, final_amount) VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, c.getCustomer_id());
		pstmt.setDouble(2, c.getDiscount());
		pstmt.setInt(3, c.getVehicle_id());
		pstmt.setDouble(4, c.getLate_return_fee());
		pstmt.setInt(5, c.getStart_mileage());
		pstmt.setInt(6, c.getEnd_mileage());
		pstmt.setString(7, c.getAny_damage_reported());
		pstmt.setDouble(8, c.getFinalAmount());
		
		int status = pstmt.executeUpdate();		
		DBConnection.dbClose();
		return status;
	}
	
	@Override
	public List<DamageReportDto> GetCustomerReport() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select c.first_name,c.last_name,ch.any_damage_reported from customer_history ch JOIN customer c ON c.id=ch.customer_id where ch.any_damage_reported='yes'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<DamageReportDto> list = new ArrayList<>();
		while (rst.next() == true) {
			String Firstname = rst.getString("first_name");
			String Lastname = rst.getString("last_name");
			String AnyDamageReported = rst.getString("any_damage_reported");
			DamageReportDto damageReportDto = new DamageReportDto(Firstname, Lastname, AnyDamageReported);
			list.add(damageReportDto);
		}
		DBConnection.dbClose();
		return list;
	}

	@Override
	public int GetTotalMileageById(int id) throws ResourceNotFoundException, SQLException, NewCustomerException {
	    Connection con = DBConnection.dbConnect();
	    String sql = "SELECT * FROM customer_history WHERE customer_id = ?";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, id);
	    ResultSet rst = pstmt.executeQuery();

	    int totalMileage = 0;
	    boolean hasResult = false;
	    while (rst.next()) {
	        hasResult = true;
	        int startMileage = rst.getInt("start_mileage");
	        int endMileage = rst.getInt("end_mileage");
	        totalMileage += (endMileage - startMileage);
	    }
	    DBConnection.dbClose();
	    if (!hasResult) {
	        throw new NewCustomerException("You don't have a completed deal");
	    }

	    return totalMileage;
	}

}
