package com.microservices.user.service;

import com.microservices.user.service.entity.Rating;
import com.microservices.user.service.external.ratingService.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Autowired
//	  RatingService ratingService;
//	@Test
//	void createRating(){
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("Very Nice Feedback").hotels(null).build();
//		Rating savedRating = ratingService.createRating(rating);
//		System.out.println("New Rating Created!!");
//	}

}
