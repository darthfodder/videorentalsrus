package com.videorentalsrus.services;

import java.util.List;

import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

public interface CustomerService {
	
	List<Customer> listAllCustomers();
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomerById(int id);
	
	List<Rental> getRentalHistory(Customer customer);
	
	List<Rental> getActiveRentals(Customer customer);
}
