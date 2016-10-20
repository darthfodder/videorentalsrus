package com.videorentalsrus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.videorentalsrus.dao.CustomerDao;
import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> listAllCustomers() {
		return customerDao.getAllCustomers();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		if(customerDao.getCustomer(customer.getCustomerId()) != null)
		{
			customerDao.updateCustomer(customer);
			return customer;
		}
		else
		{
			customerDao.insertCustomer(customer);
			return customer;
		}
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerDao.getCustomer(id);
	}

	@Override
	public List<Rental> getRentalHistory(Customer customer) {
		return customerDao.getRentalHistory(customer);
	}

	@Override
	public List<Rental> getActiveRentals(Customer customer) {
		return customerDao.getActiveRentals(customer);
	}
	
	

}
