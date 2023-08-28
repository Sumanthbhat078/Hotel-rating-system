package com.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.Repositories.UserRepository;
import com.user.dto.HotelDto;
import com.user.dto.RatingDto;
import com.user.entities.User;
import com.user.exception.ResourceNotFoundException;
import com.user.external.services.HotelService;
import com.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;
	
    @Autowired
    private RestTemplate restTemplate;

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		 return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		 User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
	        RatingDto[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), RatingDto[].class);
	        List<RatingDto> ratings = Arrays.stream(ratingsOfUser).toList();
	        ratings.stream().map((rating) -> {
	        	//ResponseEntity<HotelDto> hotelEntity=restTemplate.getForEntity("http://RATING-SERVICE/hotels"+rating.getHotelId(), HotelDto.class);
	            HotelDto hotel = hotelService.getHotel(rating.getHotelId());
	            rating.setHotelDto(hotel);
	            return rating;
	        }).collect(Collectors.toList());

	        user.setRatings(ratings);

	        return user;
	}

	@Override
	public User updateUser(User updatedUser, String userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
	    existingUser.setName(updatedUser.getName());
	    existingUser.setEmail(updatedUser.getEmail());
	    existingUser.setAbout(updatedUser.getAbout());
	    return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
		userRepository.delete(user);
	}


}
