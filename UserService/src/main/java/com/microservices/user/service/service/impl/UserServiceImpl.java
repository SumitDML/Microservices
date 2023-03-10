package com.microservices.user.service.service.impl;

import com.microservices.user.service.entity.Hotel;
import com.microservices.user.service.entity.Rating;
import com.microservices.user.service.entity.User;
import com.microservices.user.service.exception.ResourcesNotFoundException;
import com.microservices.user.service.external.hotelService.HotelService;
import com.microservices.user.service.repository.UserRepository;
import com.microservices.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourcesNotFoundException("User Not Found on the server!"));
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGS-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} ",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).collect(Collectors.toList());


        List<Rating> ratingList = ratings.stream().map(rating -> {
            //using Rest Client
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status code : "+forEntity.getStatusCode());

            //Using Feign Clients

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotels(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public String deleteUser(String userId) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
