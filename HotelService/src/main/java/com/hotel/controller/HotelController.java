package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entities.Hotel;
import com.hotel.services.HotelServices;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	@Autowired
    private HotelServices hotelService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotel));
    }
	
	@GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotel(hotelId));
    }
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
	
	@PutMapping("/{hotelId}")
	 public ResponseEntity<Hotel> updateUser(@RequestBody Hotel hotel,@PathVariable String hotelId)
	 {
		 Hotel updatedUser=hotelService.updateHotel(hotel, hotelId);
		 return new ResponseEntity<Hotel>(updatedUser,HttpStatus.OK);
	 }
	@DeleteMapping("/{hotelId}")
	 public void deleteUser(@PathVariable String hotelId)
	 {
		 hotelService.deleteHotel(hotelId);
	 }
}
