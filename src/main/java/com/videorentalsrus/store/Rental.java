package com.videorentalsrus.store;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rental implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer rentalId;
	private Integer rentalVideoId;
	private Integer rentalCustomerId;
	private LocalDate rentalDate;
	private LocalDate returnedDate;
	private LocalDate dueDate;
	public Integer getRentalId() {
		return rentalId;
	}
	public void setRentalId(Integer rentalId) {
		this.rentalId = rentalId;
	}
	public Integer getRentalVideoId() {
		return rentalVideoId;
	}
	public void setRentalVideoId(Integer rentalVideoId) {
		this.rentalVideoId = rentalVideoId;
	}
	public Integer getRentalCustomerId() {
		return rentalCustomerId;
	}
	public void setRentalCustomerId(Integer rentalCustomerId) {
		this.rentalCustomerId = rentalCustomerId;
	}
	public LocalDate getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
	public LocalDate getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
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
		Rental otherRental = (Rental)other;
		return rentalId.equals(otherRental.rentalId);
	}
	
	@Override
	public int hashCode() {
		return rentalId == null ? 0 : rentalId.hashCode();
	}
	
}
