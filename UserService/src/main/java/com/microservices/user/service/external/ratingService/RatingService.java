package com.microservices.user.service.external.ratingService;

import com.microservices.user.service.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "RATINGS-SERVICE")
public interface RatingService {
    @PostMapping("/ratings")
    Rating createRating(Rating ratings);

    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId,@RequestBody Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    void deleteRatings(@PathVariable String ratingId);

}
