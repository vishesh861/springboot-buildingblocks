package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserExistsException;
import com.stacksimplify.restservices.exception.UserNameNotFoundException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags="User Management RESTful Service",value="UserController",description="Controller for User")
@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="Retrieve List Of Users")
	@GetMapping
	public List<User> getAllUsers(){
		
		return userService.getAllUsers();
	}
	
	@ApiOperation(value="Create Users")
	@PostMapping
	public ResponseEntity<Void> createUser(@ApiParam("User Information For The Create User") @Valid @RequestBody User user,UriComponentsBuilder builder){
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);	
			return userOptional.get();
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 userService.deleteUserById(id);
	}
	
	@GetMapping("/byUserName/{username}")
	public User findByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.findByUsername(username);
		
		if(user==null) {
			throw new UserNameNotFoundException("UserName" + " " + username + " " + "Not Found In User Repository");
		}
		return user;
	}

}
