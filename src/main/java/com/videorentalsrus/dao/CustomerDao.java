package com.videorentalsrus.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerMapper customerMapper;

	@Cacheable(value = "customer")
	public Customer getCustomer(int customerId) {
		return customerMapper.getCustomer(customerId);
	}

	public List<Customer> getAllCustomers() {
		return customerMapper.getAllCustomers();
	}

	public List<Customer> findCustomersByFullName(String firstName, String lastName) {
		return customerMapper.findCustomersByFullName(firstName, lastName);
	}

	public List<Customer> findCustomersByLastName(String lastName) {
		return customerMapper.findCustomersByLastName(lastName);
	}

	public List<Customer> findCustomersByFirstName(String firstName) {
		return customerMapper.findCustomersByFirstName(firstName);
	}

	public List<Customer> findCustomersByPhoneNumber(String phoneNumber) {
		return customerMapper.findCustomersByPhoneNumber(phoneNumber);
	}

	public List<Customer> findCustomersByStreetAddress(String streetAddress) {
		return customerMapper.findCustomersByStreetAddress(streetAddress);
	}

	public List<Customer> findCustomersByZipCode(String zipCode) {
		return customerMapper.findCustomersByZipCode(zipCode);
	}

	public void insertCustomer(Customer customer) {
		customerMapper.insertCustomer(customer);
	}

	@CacheEvict(value = "customer", key = "#customer.customerId", beforeInvocation = true)
	public void updateCustomer(Customer customer) {
		customerMapper.updateCustomer(customer);
	}

	public List<Rental> getRentalHistory(Customer customer) {
		return customerMapper.getRentalHistory(customer);
	}

	public List<Rental> getActiveRentals(Customer customer) {
		return customerMapper.getActiveRentals(customer);
	}
}
