package com.microservices.hotel.service.service.Impl;

import com.microservices.hotel.service.entity.Hotel;
import com.microservices.hotel.service.exception.ResourceNotFoundException;
import com.microservices.hotel.service.repository.HotelRepository;
import com.microservices.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id not found!!!"));
    }
}
