package com.videorentalsrus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.videorentalsrus.dao.CustomerDao;
import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

	@Override
	@CacheEvict(value="customers", key="#customer.customerId")
	public Customer saveCustomer(Customer customer) {
		if (customer.getCustomerId() != null) {
			customerDao.updateCustomer(customer);
			return customer;
		} else {
			customerDao.insertCustomer(customer);
			return customer;
		}
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerDao.getCustomer(id);
	}

	@Override
	public List<Customer> findCustomersByFullName(String firstName, String lastName) {
		return customerDao.findCustomersByFullName(firstName, lastName);
	}

	@Override
	public Customer findCustomerByFullName(String firstName, String lastName) {
		List<Customer> customers = findCustomersByFullName(firstName,lastName);
		return customers.isEmpty() ? null : customers.get(0);
	}

	@Override
	public Customer findCustomersByFirstName(String firstName) {
		return customerDao.findCustomersByFirstName(firstName);
	}
	@Override
	public List<Customer> findCustomersByLastName(String lastName) {
		return customerDao.findCustomersByLastName(lastName);
	}

	@Override
	public List<Customer> findCustomersByStreetAddress(String streetAddress) {
		return customerDao.findCustomersByStreetAddress(streetAddress);
	}

	@Override
	public Customer findCustomerByStreetAddress(String streetAddress){
		List<Customer> customers = findCustomersByStreetAddress(streetAddress);
		return customers.isEmpty() ? null : customers.get(0);
	}
	
	@Override
	public List<Customer> findCustomersByZipCode(String zipCode) {
		return customerDao.findCustomersByZipCode(zipCode);
	}

	@Override
	public List<Customer> findCustomersByPhoneNumber(String phoneNumber) {
		return customerDao.findCustomersByPhoneNumber(phoneNumber);
	}

	@Override
	public Customer findCustomerByPhoneNumber(String phoneNumber) {
		List<Customer> customers = findCustomersByPhoneNumber(phoneNumber);
		return customers.isEmpty() ? null : customers.get(0);
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
