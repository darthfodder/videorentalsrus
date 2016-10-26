package com.videorentalsrus.services;

import java.util.List;

import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

public interface CustomerService {
	
	List<Customer> getAllCustomers();
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomerById(int id);
	
	List<Customer> findCustomersByFullName(String firstName,String lastName);
	
	Customer findCustomerByFullName(String firstName,String lastName);
	
	List<Customer> findCustomersByLastName(String lastName);
	
	List<Customer> findCustomersByFirstName(String firstName);
	
	Customer findCustomerByStreetAddress(String streetAddress);
	
	List<Customer> findCustomersByPhoneNumber(String phoneNumber);
	
	Customer findCustomerByPhoneNumber(String phoneNumber);
	
	List<Customer> findCustomersByStreetAddress(String streetAddress);
	
	List<Customer> findCustomersByZipCode(String zipCode);
	
	List<Rental> getRentalHistory(Customer customer);
	
	List<Rental> getActiveRentals(Customer customer);

	

	
}
