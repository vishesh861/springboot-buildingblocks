package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.repositories.UserRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders for a user
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		
		java.util.Optional<com.stacksimplify.restservices.entities.User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		return userOptional.get().getOrders();
	}
	
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid,@RequestBody Order order) throws UserNotFoundException {
		
		java.util.Optional<com.stacksimplify.restservices.entities.User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		
	    com.stacksimplify.restservices.entities.User user = (com.stacksimplify.restservices.entities.User) userOptional.get();
	    order.setUser(user);
	   return orderRepository.save(order);
	    
	    
	}
	
	public List<Order>getOrderByOrderId(@PathVariable Long userid,Long orderid) throws UserNotFoundException{
		
		java.util.Optional<com.stacksimplify.restservices.entities.User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("user not found");
		}
		return userOptional.get().getOrders();
	}
	
	
	
	
}
