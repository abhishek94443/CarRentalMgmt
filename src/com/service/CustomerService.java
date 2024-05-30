package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.dto.CustomerDetailsDto;
import com.model.Customer;

public class CustomerService {
	CustomerDao dao = new CustomerDaoImpl();
	
	public int insert(Customer customer) throws SQLException {
		return dao.save(customer);
	}
	
	public Customer getCustomer(int id) throws SQLException {
		return dao.getCustomer(id);
	}
	
	public Customer particularCustomer(int id) throws SQLException {
		return dao.particularCustomer(id);
	}
	
	public List<Customer> getAllCustomers() throws SQLException {
		return dao.getAllCustomers();
	}

	public int blacklistCustomer(int id) throws SQLException {
		return dao.blacklistCustomer(id);		
	}

	public List<CustomerDetailsDto> getDetailsForVendor(int vendorId) throws SQLException{
		return dao.getDetailsForVendor(vendorId);
	}
}
