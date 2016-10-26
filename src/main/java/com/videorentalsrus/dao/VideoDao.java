package com.videorentalsrus.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.RentalType;
import com.videorentalsrus.store.Video;

@Repository
public class VideoDao {

	@Autowired
	private VideoMapper videoMapper;

	@Cacheable(value = "video", key = "#videoId")
	public Video getVideo(int videoId) {
		return videoMapper.getVideo(videoId);
	}

	public List<Video> getAllVideos() {
		return videoMapper.getAllVideos();
	}

	public List<RentalType> getRentalTypes() {
		return videoMapper.getRentalTypes();
	}

	@Cacheable(value = "rentalType", key = "#rentalTypeId")
	public RentalType getRentalType(int rentalTypeId) {
		return videoMapper.getRentalType(rentalTypeId);
	}

	public void insertRentalType(RentalType rentalType) {
		videoMapper.insertRentalType(rentalType);
	}

	@CacheEvict(value = "rentalType", key = "#rentalType.rentalTypeId", beforeInvocation = true)
	public void updateRentalType(RentalType rentalType) {
		videoMapper.updateRentalType(rentalType);
	}

	public List<Video> getActiveVideos() {
		return videoMapper.getActiveVideos();
	}

	public List<Video> getAvailableVideos() {
		return videoMapper.getAvailableVideos();
	}

	public List<Video> findVideosByTitle(String title) {
		return videoMapper.findVideosByTitle(title);
	}

	public List<Video> findVideosByGenre(String genre) {
		return videoMapper.findVideosByGenre(genre);
	}

	public List<Video> findVideosByYear(int year) {
		return videoMapper.findVideosByYear(year);
	}

	public List<Video> findVideosByRentalType(String rentalType) {
		return videoMapper.findVideosByRentalType(rentalType);
	}

	public void insertVideo(Video video) {
		videoMapper.insertVideo(video);
	}

	@CacheEvict(value = "video", key = "#video.videoId", beforeInvocation = true)
	public void updateVideo(Video video) {
		videoMapper.updateVideo(video);
	}

	public List<Rental> getRentalHistory(Video video) {
		return videoMapper.getRentalHistory(video);
	}
}
