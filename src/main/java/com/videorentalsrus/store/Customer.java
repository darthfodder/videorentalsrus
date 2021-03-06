package com.videorentalsrus.store;

import java.io.Serializable;


public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer customerId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String streetAddress;
	private String zipCode;
	private Double balance;
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other)
		{
			return true;
		}
		
		if(other == null || this.getClass() != other.getClass())
		{
			return false;
		}
		Customer otherCustomer = (Customer)other;
		return customerId.equals(otherCustomer.customerId);
	}
	
	@Override
	public int hashCode() {
		return customerId == null ? 0 : customerId.hashCode();
	}
	

}
