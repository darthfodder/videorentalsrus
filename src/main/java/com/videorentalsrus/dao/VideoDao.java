package com.videorentalsrus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.RentalType;
import com.videorentalsrus.store.Video;

@Mapper
public interface VideoDao {

	public Video getVideo(int videoId);
	public List<Video> getAllVideos();
	public List<RentalType> getRentalTypes();
	public RentalType getRentalType(int rentalTypeId);
	public void insertRentalType(RentalType rentalType);
	public void updateRentalType(RentalType rentalType);
	public List<Video> getActiveVideos();
	public List<Video> getAvailableVideos();
	public List<Video> findVideosByTitle(String title);
	public List<Video> findVideosByGenre(String genre);
	public List<Video> findVideosByYear(int year);
	public List<Video> findVideosByRentalType(String rentalType);
	public void insertVideo(Video video);
	public void updateVideo(Video video);
	public List<Rental> getRentalHistory(Video video);
}
