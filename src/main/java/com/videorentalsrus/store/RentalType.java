package com.videorentalsrus.store;

import java.io.Serializable;

public class RentalType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer rentalTypeId;
	private String name;
	private Integer rentalDuration;
	private Double rentalFee;
	private Double dailyLateFee;
	
	public Integer getRentalTypeId() {
		return rentalTypeId;
	}
	public void setRentalTypeId(Integer rentalTypeId) {
		this.rentalTypeId = rentalTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public Double getRentalFee() {
		return rentalFee;
	}
	public void setRentalFee(Double rentalFee) {
		this.rentalFee = rentalFee;
	}
	public Double getDailyLateFee() {
		return dailyLateFee;
	}
	public void setDailyLateFee(Double dailyLateFee) {
		this.dailyLateFee = dailyLateFee;
	}

}
