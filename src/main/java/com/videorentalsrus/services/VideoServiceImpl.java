package com.videorentalsrus.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.videorentalsrus.dao.VideoDao;
import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.RentalType;
import com.videorentalsrus.store.Video;

public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoDao videoDao;
	
	@Override
	public Video saveVideo(Video video) {
		if(videoDao.getVideo(video.getVideoId()) == null)
		{
			videoDao.insertVideo(video);
			return video;
		}
		else
		{
			videoDao.updateVideo(video);
			return video;
		}
	}

	@Override
	public List<Video> getAllVideos() {
		return videoDao.getAllVideos();
	}

	@Override
	public List<Video> getAllActiveVideos() {
		return videoDao.getActiveVideos();
	}

	@Override
	public List<Video> getAllAvailableVideos() {
		return videoDao.getAvailableVideos();
	}

	@Override
	public List<Video> getVideosMatchingTitle(String title) {
		return videoDao.findVideosByTitle(title);
	}
	
	@Override
	public List<Video> getAvailableVideosMatchingTitle(String title) {
		return getAvailableVideosFromList(getVideosMatchingTitle(title));
	}

	@Override
	public List<Video> getVideosByYear(int year) {
		return videoDao.findVideosByYear(year);
	}

	@Override
	public List<Video> getAvailabletVideosByYear(int year) {
		return getAvailableVideosFromList(getVideosByYear(year));
	}
	
	@Override
	public List<Video> getVideosByGenre(String genre) {
		return videoDao.findVideosByGenre(genre);
	}
	
	@Override
	public List<Video> getAvailableVideosByGenre(String genre)
	{
		return getAvailableVideosFromList(getVideosByGenre(genre));
	}

	@Override
	public List<Video> getVideosByRentalType(RentalType rentalType) {
		return videoDao.findVideosByRentalType(rentalType);
	}
	
	@Override
	public List<Video> getAvailableVideosByRentalType(RentalType rentalType) {
		return getAvailableVideosFromList(getVideosByRentalType(rentalType));
	}
	
	@Override
	public Video getVideoById(int id) {
		return videoDao.getVideo(id);
	}
	
	@Override
	public RentalType getVideoRentalType(Video video) {
		return videoDao.getRentalType(video.getVideoRentalTypeId());
	}

	@Override
	public RentalType saveRentalType(RentalType rentalType) {
		if(videoDao.getRentalType(rentalType.getRentalTypeId()) == null)
		{
			videoDao.insertRentalType(rentalType);
			return rentalType;
		}
		else
		{
			videoDao.updateRentalType(rentalType);
			return rentalType;
		}
	}

	@Override
	public RentalType getRentalTypeById(int id) {
		return videoDao.getRentalType(id);
	}

	@Override
	public Boolean isVideoAvailable(Video video) {
		return getRentalHistory(video).get(0).getReturnedDate() != null;
	}
	
	@Override
	public List<Rental> getRentalHistory(Video video) {
		return videoDao.getRentalHistory(video);
	}
	
	private List<Video> getAvailableVideosFromList(List<Video> videoList)
	{
		return videoList.stream().filter(v -> isVideoAvailable(v)).collect(Collectors.toList());
	}
	
}
