package com.videorentalsrus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

@Mapper
public interface CustomerMapper {
	
	public Customer getCustomer(int customerId);
	public List<Customer> getAllCustomers();
	public List<Customer> findCustomersByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	public List<Customer> findCustomersByLastName(String lastName);
	public List<Customer> findCustomersByFirstName(String firstName);
	public List<Customer> findCustomersByPhoneNumber(String phoneNumber);
	public List<Customer> findCustomersByStreetAddress(String streetAddress);
	public List<Customer> findCustomersByZipCode(String zipCode);
	public void insertCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public List<Rental> getRentalHistory(Customer customer);
	public List<Rental> getActiveRentals(Customer customer);
}
