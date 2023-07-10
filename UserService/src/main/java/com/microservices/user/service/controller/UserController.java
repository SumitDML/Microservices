package com.microservices.user.service.controller;

import com.microservices.user.service.entity.User;
import com.microservices.user.service.service.UserService;
import com.microservices.user.service.service.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        User user1 = userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
    int count = 1;

    @GetMapping(value ="/{userId}")
//    @CircuitBreaker(name = "ratingsHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingsHotelRetry",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        logger.info("Retry Count: "+count);
        count++;
        User user1 = userService.getUser(userId);
        return ResponseEntity.ok(user1);
    }
    //Fallback Method!!

    public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
//        logger.info("Fallback Method is called as service is down: "+ex.getMessage());
        User user = User.builder().name("Fallback User").email("Fallback@gmail.com").about("Service is Down!").userId("12345").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> user1 = userService.getAllUser();
        return ResponseEntity.ok(user1);
    }
}
