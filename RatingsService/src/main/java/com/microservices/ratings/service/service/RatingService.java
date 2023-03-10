package com.microservices.ratings.service.service;

import com.microservices.ratings.service.entity.Ratings;

import java.util.List;

public interface RatingService {
    Ratings create(Ratings ratings);
    List<Ratings> getRatings();

    List<Ratings> getRatingsByUserId(String userId);

    List<Ratings> getRatingsByHotelId(String hotelId);

    Ratings updateRatings(String ratingId,Ratings ratings);

    void deleteRatings(String ratingId);
}
