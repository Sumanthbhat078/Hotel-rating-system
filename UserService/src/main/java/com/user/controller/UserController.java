package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entities.User;
import com.user.service.impl.UserServiceImpl;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    
    @GetMapping("/{userId}")
   // @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
    @Retry(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
    	User fallBackUser=new User();
    	fallBackUser.setUserId(userId);
    	fallBackUser.setName("dummy");
    	fallBackUser.setAbout("this is a dummy user created because some service is down");
    	return new ResponseEntity<User>(fallBackUser,HttpStatus.OK);
    	//throw new ServiceDownException("some service is down, cannot be called!!");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId)
    {
    	User updatedUser=userService.updateUser(user,userId);
    	return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
    }
    
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId)
    {
    	userService.deleteUser(userId);
    }
    
}
