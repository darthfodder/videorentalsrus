package com.videorentalsrus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheEvict;

import com.videorentalsrus.store.Rental;

@Mapper
public interface RentalMapper {

	public Rental getRental(int rentalId);
	public List<Rental> getAllRentals();
	public List<Rental> getActiveRentals();
	public void insertRental(Rental rental);
	public void updateRental(Rental rental);
	public void deleteRental(Rental rental);
}
