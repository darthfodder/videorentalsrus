package com.videorentalsrus.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.videorentalsrus.services.CustomerService;
import com.videorentalsrus.services.RentalService;
import com.videorentalsrus.services.VideoService;
import com.videorentalsrus.store.Customer;
import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.Video;

@RestController
@RequestMapping("/rentals")
public class RentalController {
	
	@Autowired
	public RentalService rentalService;
	
	@Autowired
	public VideoService videoService;
	
	@Autowired
	public CustomerService customerService;

	@Cacheable("rentals")
	@RequestMapping(value = "/{rentalId}", method = RequestMethod.GET)
	public @ResponseBody Rental getRental(@PathVariable("rentalId") int rentalId) {
		return rentalService.getRentalById(rentalId);
	}
	
	@CacheEvict(value = "rentals",key = "#rentalId")
	@RequestMapping(value = "/{rentalId}/delete", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<?> deleteRental(@PathVariable("rentalId") int rentalId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Rental rental = rentalService.deleteRental(rentalService.getRentalById(rentalId));
		if(rental == null)
		{
			return new ResponseEntity<String>("That rental does not exist",headers, HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<Rental>(rental,headers,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Rental> getRentals()
	{
		return rentalService.getActiveRentals();
	}
	
	@RequestMapping(value = "/rent", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> rentVideo(@RequestParam(value = "customerId",required = true) int customerId,
			@RequestParam(value = "videoId",required = true) int videoId)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Video video = videoService.getVideoById(videoId);
		Customer customer = customerService.getCustomerById(customerId);
		Rental rental = null;
		boolean isVideoAvailable = videoService.isVideoAvailable(video);
		if(isVideoAvailable && customer != null)
		{
			rental = rentalService.rentVideo(video, customer);
		}
		if(rental != null)
		{
			return new ResponseEntity<Rental>(rental,headers, HttpStatus.CREATED);
		}
		if(customer == null || video == null)
		{
			return new ResponseEntity<String>("Video and/or customer do not exist",headers,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(!isVideoAvailable)
		{
			return new ResponseEntity<String>("That video is unavailable",headers,HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>("Something went wrong when creating the rental",headers,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/return", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> returnVideo(@RequestParam(value = "videoId",required = true)int videoId)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Rental rental =  null;
		Video video = videoService.getVideoById(videoId);
		boolean isVideoRented = videoService.isVideoRented(video);
		if(isVideoRented)
		{
			rental = rentalService.returnVideo(video);
		}
		if(rental != null)
		{
			return new ResponseEntity<Rental>(rental,headers,HttpStatus.OK);
		}
		if(video == null)
		{
			return new ResponseEntity<String>("Video does not exist",headers,HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if(!isVideoRented)
		{
			return new ResponseEntity<String>("Video is not currently rented", headers,HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>("Something went wrong, video was not returned",headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
