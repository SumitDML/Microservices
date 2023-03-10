package com.microservices.ratings.service.repository;

import com.microservices.ratings.service.entity.Ratings;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Ratings,String> {
    List<Ratings> findByUserId(String userId);
    List<Ratings> findByHotelId(String hotelId);
}
