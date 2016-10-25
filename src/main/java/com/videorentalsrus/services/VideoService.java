package com.videorentalsrus.services;

import java.util.List;

import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.RentalType;
import com.videorentalsrus.store.Video;

public interface VideoService {
	
	
	public Video saveVideo(Video video);
	
	public List<Video> getAllVideos();
	
	public List<Video> getAllActiveVideos();
	
	public List<Video> getAllAvailableVideos();
	
	public List<Video> getVideosMatchingTitle(String title);
	
	public List<Video> getVideosByYear(int year);
	
	public List<Video> getVideosByGenre(String genre);
	
	public List<Video> getVideosByRentalType(String rentalType);
	
	public List<Video> getVideosByRentalType(RentalType rentalType);
	
	public Video getVideoById(int id);
	
	public RentalType getVideoRentalType(Video video);
	
	public RentalType saveRentalType(RentalType rentalType);
	
	public RentalType getRentalTypeById(int id);
	
	public List<RentalType> getRentalTypes();
	
	public Boolean isVideoAvailable(Video video);
	
	public Boolean isVideoRented(Video video);

	public List<Video> getAvailableVideosMatchingTitle(String title);

	public List<Video> getAvailabletVideosByYear(int year);

	public List<Video> getAvailableVideosByGenre(String genre);

	public List<Video> getAvailableVideosByRentalType(String rentalType);
	
	public List<Video> getAvailableVideosByRentalType(RentalType rentalType);
	
	public List<Rental> getRentalHistory(Video video);

}
