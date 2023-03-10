package com.microservices.ratings.service.service.Impl;

import com.microservices.ratings.service.entity.Ratings;
import com.microservices.ratings.service.exception.ResourcesNotFoundException;
import com.microservices.ratings.service.repository.RatingRepository;
import com.microservices.ratings.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Ratings create(Ratings ratings) {
        return ratingRepository.save(ratings);
    }

    @Override
    public List<Ratings> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Ratings> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Ratings> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Ratings updateRatings(String ratingId, Ratings ratings) {
        Ratings ratings1 = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourcesNotFoundException("Rating not found"));
        ratings1.setRatingId(ratings.getRatingId());
        ratings1.setRating(ratings.getRating());
        ratings1.setFeedback(ratings.getFeedback());
        ratings1.setUserId(ratings.getUserId());
        ratings1.setHotelId(ratings.getHotelId());
        return ratingRepository.save(ratings1);
    }

    @Override
    public void deleteRatings(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
