package com.sbil.springdemo.service;

import com.sbil.springdemo.exception.ResourceNotFoundException;
import com.sbil.springdemo.model.Hotel;
import com.sbil.springdemo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImplimentation implements  HotelService
{
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel)
    {

        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);

        return hotelRepository.save(hotel);

    }

    @Override
    public List<Hotel> getAllHotel()
    {

        return hotelRepository.findAll();


    }

    @Override
    public Hotel getHotel(String hotelId)
    {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with given id is not found on server !!"+hotelId));
    }
}
