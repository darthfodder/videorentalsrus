package com.videorentalsrus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.videorentalsrus.store.Rental;

@Mapper
public interface RentalDao {
	
	public Rental getRental(int rentalId);
	public List<Rental> getAllRentals();
	public List<Rental> getActiveRentals();
	public void insertRental(Rental rental);
	public void updateRental(Rental rental);
}
