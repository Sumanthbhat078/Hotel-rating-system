package com.hotel.services;

import java.util.List;

import com.hotel.entities.Hotel;

public interface HotelServices {
    Hotel createHotel(Hotel hotel);
    Hotel getHotel(String hotelId);
    List<Hotel> getAllHotels();
    Hotel updateHotel(Hotel hotel,String hotelId);
    void deleteHotel(String hotelId);
}
