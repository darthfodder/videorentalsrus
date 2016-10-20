package com.videorentalsrus.store;

import java.io.Serializable;

import com.videorentalsrus.enums.VideoStatus;

public class Video implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer videoId;
	private String title;
	private String genre;
	private Integer year;
	private VideoStatus videoStatus;
	private Integer videoRentalTypeId;
	
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public VideoStatus getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(VideoStatus videoStatus) {
		this.videoStatus = videoStatus;
	}
	public Integer getVideoRentalTypeId() {
		return videoRentalTypeId;
	}
	public void setVideoRentalTypeId(Integer videoRentalTypeId) {
		this.videoRentalTypeId = videoRentalTypeId;
	}

}
