package com.hotel.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entities.Hotel;
import com.hotel.entities.exception.ResourceNotFoundException;
import com.hotel.repositories.HotelRepository;
import com.hotel.services.HotelServices;

@Service
public class HotelServiceImpl implements HotelServices{

	@Autowired
    private HotelRepository hotelRepository;
	
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotel(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel with given id "+hotelId+" not found !!"));
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel updateHotel(Hotel updatedHotel, String hotelId) {
		Hotel existingHotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel with given id "+hotelId+" not found !!"));
		existingHotel.setName(updatedHotel.getName());
		existingHotel.setLocation(updatedHotel.getLocation());
		existingHotel.setAbout(updatedHotel.getAbout());
		return hotelRepository.save(existingHotel);
	}

	@Override
	public void deleteHotel(String hotelId) {
		Hotel hotel=hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("hotel with given id "+hotelId+" not found !!"));
		hotelRepository.delete(hotel);
	}

}
