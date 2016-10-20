package com.videorentalsrus.services;

import java.util.List;

import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.Video;

public interface RentalService {
	
	public Rental rentVideo(Video video, Customer customer);
	public Rental returnVideo(Video video);
	public Rental getRentalById(int id);
	public Video getVideoOfRental(Rental rental);
	public Rental getMostRecentVideoRental(Video video);
	public Customer getCustomerOfRental(Rental rental);
	public List<Rental> getRentalHistory();
	public List<Rental> getActiveRentals();

}
