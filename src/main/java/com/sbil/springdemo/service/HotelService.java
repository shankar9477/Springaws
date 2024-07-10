package com.sbil.springdemo.service;

import com.sbil.springdemo.model.Hotel;

import java.util.List;

public interface HotelService
{

    Hotel saveHotel(Hotel hotel);

    List<Hotel> getAllHotel();

    Hotel getHotel(String hotelId);
}
