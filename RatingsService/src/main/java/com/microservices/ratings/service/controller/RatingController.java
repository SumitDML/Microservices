package com.microservices.ratings.service.controller;

import com.microservices.ratings.service.entity.Ratings;
import com.microservices.ratings.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Ratings> createRatings(@RequestBody Ratings ratings){
        String ratingId = UUID.randomUUID().toString();
        ratings.setRatingId(ratingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(ratings));
    }
    @GetMapping
    public ResponseEntity<List<Ratings>>  getRatings(){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Ratings>>  getRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByUserId(userId));
    }
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Ratings>>  getRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByHotelId(hotelId));
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Ratings>  updateRating(@PathVariable String ratingId,@RequestBody Ratings rating) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.updateRatings(ratingId, rating));
    }
    @DeleteMapping("/{ratingId}")
    public void deleteRatings(@PathVariable String ratingId){
        ratingService.deleteRatings(ratingId);
    }


}
