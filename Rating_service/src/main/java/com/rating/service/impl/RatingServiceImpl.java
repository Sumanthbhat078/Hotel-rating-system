package com.rating.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.Repository.RatingRepository;
import com.rating.entities.Rating;
import com.rating.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {


    @Autowired
    private RatingRepository repository;

    @Override
    public Rating createRating(Rating rating) {
    	String ratingId=UUID.randomUUID().toString();
    	rating.setRatingId(ratingId);
        return repository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }

	@Override
	public boolean hasUserRatedHotel(String userId, String hotelId) {
		return repository.existsByUserIdAndHotelId(userId, hotelId);
	}
}
