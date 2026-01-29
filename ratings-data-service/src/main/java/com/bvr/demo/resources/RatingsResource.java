package com.bvr.demo.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvr.demo.models.Rating;
import com.bvr.demo.models.UserRating;

@RestController
@RequestMapping("/ratings-data")
public class RatingsResource {

	public RatingsResource() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/movies/{movieId}")
	public Rating getMovieRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 5);
	}
	
	
	@RequestMapping("/user/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		
		userRating.initData(userId);
		return userRating;
	}
}
