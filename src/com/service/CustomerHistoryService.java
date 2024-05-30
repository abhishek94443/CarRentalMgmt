package com.service;

import com.dao.CustomerHistoryDao;
import com.dao.CustomerHistoryDaoImpl;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.dto.CustomerHistoryDto;
import com.dto.DamageReportDto;
import com.exception.NewCustomerException;
import com.exception.ResourceNotFoundException;
import com.exception.WrongInformationException;
import com.model.CustomerHistory;
import com.model.Lease;

import java.sql.SQLException;
import java.util.List;


public class CustomerHistoryService {
	CustomerHistoryDao customerhistoryDao=new CustomerHistoryDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();

	public List<CustomerHistoryDto> findAll(int id) throws SQLException {
		return customerhistoryDao.findAll(id);
	}
	
	public List<Lease> GetOngoingDeals(int id) throws SQLException {
		return customerhistoryDao.GetOngoingDeals(id);
	}
	
	public List<Lease> GetPendingDeals(int id) throws SQLException {
		return customerhistoryDao.GetPendingDeals(id);
	}

	public int insertIntoHistory(CustomerHistory customerHistory) throws SQLException, WrongInformationException{
		int status = customerhistoryDao.insertIntoHistory(customerHistory);
		if(status != 1) {
			throw new WrongInformationException("Invalid information");
		}
		return status;
	}
	
	public List<DamageReportDto> GetCustomerReport() throws SQLException {
		return customerhistoryDao.GetCustomerReport();
	}
	
	public int GetTotalmileageById(int id) throws SQLException,ResourceNotFoundException, NewCustomerException{
		return customerhistoryDao.GetTotalMileageById(id);
	}

	
}
