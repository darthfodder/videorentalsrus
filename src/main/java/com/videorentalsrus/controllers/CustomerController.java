package com.videorentalsrus.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.videorentalsrus.services.CustomerService;
import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	public CustomerService customerService;

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public @ResponseBody Customer getCustomer(@PathVariable("customerId") int customerId) {
		return customerService.getCustomerById(customerId);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomers(
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "lastName", required = false) String lastName,
			@RequestParam(value = "streetAddress", required = false) String streetAddress,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "zipCode", required = false) String zipCode) {

		List<Customer> customers = customerService.getAllCustomers();
		Stream<Customer> filteredCustomers = customers.stream();
		if (firstName != null) {
			filteredCustomers = filteredCustomers.filter(c -> c.getFirstName().equals(firstName));
		}
		if (lastName != null) {
			filteredCustomers = filteredCustomers.filter(c -> c.getLastName().equals(lastName));
		}
		if (streetAddress != null) {
			filteredCustomers = filteredCustomers.filter(c -> c.getStreetAddress().equals(streetAddress));
		}
		if (phoneNumber != null) {
			filteredCustomers = filteredCustomers.filter(c -> c.getPhoneNumber().equals(phoneNumber));
		}
		if (zipCode != null) {
			filteredCustomers = filteredCustomers.filter(c -> c.getZipCode().equals(zipCode));
		}
		return filteredCustomers.collect(Collectors.toList());
	}

	@RequestMapping(value = "/{customerId}/rentalHistory", method = RequestMethod.GET)
	public @ResponseBody List<Rental> getRentalHistory(@PathVariable("customerId") int customerId) {
		return customerService.getRentalHistory(customerService.getCustomerById(customerId));
	}

	@RequestMapping(value = "/{customerId}/currentRentals", method = RequestMethod.GET)
	public @ResponseBody List<Rental> getCustomerCurrentRentals(@PathVariable("customerId") int customerId) {
		return customerService.getActiveRentals(customerService.getCustomerById(customerId));
	}

	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus successStatus;
		if (customer.getCustomerId() == null) {
			successStatus = HttpStatus.CREATED;
			customer.setBalance(0d);
		} else {
			successStatus = HttpStatus.OK;
		}
		Customer temp = customerService.saveCustomer(customer);

		if (temp != null && customer.getCustomerId() != null) {
			return new ResponseEntity<Customer>(customer, headers, successStatus);
		} else {
			return new ResponseEntity<String>("Something went wrong, customer not saved", headers,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
