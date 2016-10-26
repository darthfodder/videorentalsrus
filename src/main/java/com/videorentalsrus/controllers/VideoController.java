package com.videorentalsrus.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.videorentalsrus.services.VideoService;
import com.videorentalsrus.store.Rental;
import com.videorentalsrus.store.RentalType;
import com.videorentalsrus.store.Video;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/videos")
public class VideoController {

	@Autowired
	public VideoService videoService;

	@RequestMapping(value = "/{videoId}", method = RequestMethod.GET)
	public @ResponseBody Video getVideo(@PathVariable("videoId") int videoId) {
		return videoService.getVideoById(videoId);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Video> getVideos(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "rentalType", required = false) String rentalType) {
		List<Video> videos = videoService.getAllVideos();
		return filterVideos(videos, title, genre, year, rentalType);
	}

	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public @ResponseBody List<Video> getAvailableVideos(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "rentalType", required = false) String rentalType) {
		List<Video> videos = videoService.getAllAvailableVideos();
		return filterVideos(videos, title, genre, year, rentalType);
	}

	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public @ResponseBody List<Video> getActiveVideos(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "year", required = false) Integer year,
			@RequestParam(value = "rentalType", required = false) String rentalType) {
		List<Video> videos = videoService.getAllActiveVideos();

		return filterVideos(videos, title, genre, year, rentalType);
	}

	@RequestMapping(value = "/rentalTypes/save", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<RentalType> saveRentalType(@RequestBody RentalType rentalType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus successStatus;
		if (rentalType.getRentalTypeId() == null) {
			successStatus = HttpStatus.CREATED;
		} else {
			successStatus = HttpStatus.OK;
		}
		RentalType temp = videoService.saveRentalType(rentalType);

		if (temp != null && rentalType.getRentalTypeId() != null) {
			return new ResponseEntity<RentalType>(rentalType, headers, successStatus);
		} else {
			return new ResponseEntity<RentalType>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/rentalTypes", method = RequestMethod.GET)
	public @ResponseBody List<RentalType> getRentalTypes() {
		return videoService.getRentalTypes();
	}

	@RequestMapping(value = "/{videoId}/rentalHistory", method = RequestMethod.GET)
	public @ResponseBody List<Rental> getRentalHistory(@PathVariable("videoId") int videoId) {
		return videoService.getRentalHistory(videoService.getVideoById(videoId));
	}

	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Video> saveVideo(@RequestBody Video video) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus successStatus;
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (video.getVideoId() == null) {
			successStatus = HttpStatus.CREATED;
		} else {
			successStatus = HttpStatus.OK;
		}
		Video temp = videoService.saveVideo(video);
		if (temp != null && videoService.getVideoById(temp.getVideoId()) != null) {
			return new ResponseEntity<Video>(video, headers, successStatus);
		} else {
			return new ResponseEntity<Video>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private List<Video> filterVideos(List<Video> videos, String title, String genre, Integer year, String rentalType) {
		Stream<Video> filteredVideos = videos.stream();

		if (title != null) {
			filteredVideos = filteredVideos.filter(v -> v.getTitle().toUpperCase().contains(title.toUpperCase()));
		}
		if (genre != null) {
			filteredVideos = filteredVideos.filter(v -> v.getGenre().equals(genre));
		}
		if (year != null) {
			filteredVideos = filteredVideos.filter(v -> v.getYear().equals(year));
		}

		videos = filteredVideos.collect(Collectors.toList());

		if (rentalType != null) {
			videos.retainAll(videoService.getVideosByRentalType(rentalType));
		}
		return videos;
	}

}
