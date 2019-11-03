package com.stacksimplify.restservices.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.dtos.UserDtoV1;
import com.stacksimplify.restservices.dtos.UserDtoV2;
import com.stacksimplify.restservices.dtos.UserMmDtos;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value="/versioning/params/users")
public class UserRequestParameterVersioningController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value="/{id}",params="version=1")
	public UserDtoV1 getUserById(@PathVariable("id") Long id) {
		try {
			Optional<User> optionalUser = userService.getUserById(id);	
			
			if(!optionalUser.isPresent()) {
				throw new UserNotFoundException("user not present");
			}
			
			User user = optionalUser.get();
			
			UserDtoV1 userDtoV1 = modelMapper.map(user,UserDtoV1.class);
			
			return userDtoV1;
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
	}
	
	
	@GetMapping(value="/{id}",params="version=2")
	public UserDtoV2 getUserById1(@PathVariable("id") Long id) {
		try {
			Optional<User> optionalUser = userService.getUserById(id);	
			
			if(!optionalUser.isPresent()) {
				throw new UserNotFoundException("user not present");
			}
			
			User user = optionalUser.get();
			
			UserDtoV2 userDtoV2 = modelMapper.map(user,UserDtoV2.class);
			
			return userDtoV2;
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
	}
}
