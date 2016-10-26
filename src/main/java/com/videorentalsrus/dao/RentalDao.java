package com.videorentalsrus.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.videorentalsrus.store.Rental;

@Repository
public class RentalDao {

	@Autowired
	RentalMapper rentalMapper;

	@Cacheable(value = "rental", key = "#rentalId")
	public Rental getRental(int rentalId) {
		return rentalMapper.getRental(rentalId);
	}

	public List<Rental> getAllRentals() {
		return rentalMapper.getAllRentals();
	}

	public List<Rental> getActiveRentals() {
		return rentalMapper.getActiveRentals();
	}

	public void insertRental(Rental rental) {
		rentalMapper.insertRental(rental);
	}

	@CacheEvict(value = "rental", key = "#rental.rentalId", beforeInvocation = true)
	public void updateRental(Rental rental) {
		rentalMapper.updateRental(rental);
	}

	@CacheEvict(value = "rental", key = "#rental.rentalId", beforeInvocation = true)
	public void deleteRental(Rental rental) {
		rentalMapper.deleteRental(rental);
	}
}
