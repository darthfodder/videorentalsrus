package com.videorentalsrus.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.videorentalsrus.dao.RentalDao;
import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.Video;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	RentalDao rentalDao;

	@Autowired
	VideoService videoService;

	@Autowired
	CustomerService customerService;

	@Override
	public Rental rentVideo(Video video, Customer customer) {
		if (videoService.isVideoAvailable(video)) {
			Rental rental = new Rental();
			LocalDate now = LocalDate.now();
			LocalDate dueDate = now.plus(Period.ofDays(videoService.getVideoRentalType(video).getRentalDuration()));
			rental.setRentalDate(now);
			rental.setDueDate(dueDate);
			rental.setRentalCustomerId(customer.getCustomerId());
			rental.setRentalVideoId(video.getVideoId());
			rentalDao.insertRental(rental);
			return rental;
		}
		return null;
	}

	@Override
	public Rental returnVideo(Video video) {
		if (!videoService.isVideoAvailable(video)) {
			Rental rental = getMostRecentVideoRental(video);
			Customer customer = customerService.getCustomerById(rental.getRentalCustomerId());
			rental.setReturnedDate(LocalDate.now());
			Double lateFee = ChronoUnit.DAYS.between(rental.getDueDate(), rental.getReturnedDate())
					* videoService.getVideoRentalType(video).getDailyLateFee();
			customer.setBalance(customer.getBalance() + lateFee);
			customerService.saveCustomer(customer);
			rentalDao.updateRental(rental);
			return rental;
		}
		return null;
	}
	
	@Override
	@Caching(evict = { @CacheEvict(value = "rentals", key = "#rental.rentalId", beforeInvocation = true),
			@CacheEvict(value = "customer_rentalhistory", key = "#rental.rentalCustomerId"),
			@CacheEvict(value = "customer_rentals", key = "#rental.rentalId") })
	public Rental deleteRental(Rental rental)
	{
		rentalDao.deleteRental(rental);
		return rental;
	}

	@Override
	public Rental getRentalById(int id) {
		// TODO Auto-generated method stub
		return rentalDao.getRental(id);
	}

	@Override
	public Video getVideoOfRental(Rental rental) {
		return videoService.getVideoById(rental.getRentalVideoId());
	}

	@Override
	public Rental getMostRecentVideoRental(Video video) {
		return videoService.getRentalHistory(video).get(0);
	}

	@Override
	public Customer getCustomerOfRental(Rental rental) {
		return customerService.getCustomerById(rental.getRentalCustomerId());
	}

	@Override
	public List<Rental> getRentalHistory() {
		return rentalDao.getAllRentals();
	}

	@Override
	public List<Rental> getActiveRentals() {
		return rentalDao.getActiveRentals();
	}

}
