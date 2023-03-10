package com.microservices.hotel.service.service;

import com.microservices.hotel.service.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotelService {
    //create
    Hotel create(Hotel hotel);
    //get all
    List<Hotel> getAll();
    //get single
    Hotel get(String id);


}
