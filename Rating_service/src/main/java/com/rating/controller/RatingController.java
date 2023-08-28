package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.entities.Rating;
import com.rating.payload.ApiResponse;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody Rating rating) {
        if(ratingService.hasUserRatedHotel(rating.getUserId(),rating.getHotelId()))
        {
        	ApiResponse response=new ApiResponse("user has already rated this hotel, please rate other hotel or update the rating",false);
            return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
        }
        ratingService.createRating(rating);
    	ApiResponse response=new ApiResponse("rating saved successfully",true);
    	return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    //get all of hotels
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }


}
