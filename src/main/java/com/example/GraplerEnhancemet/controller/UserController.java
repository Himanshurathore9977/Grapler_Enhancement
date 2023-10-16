package com.example.GraplerEnhancemet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.GraplerEnhancemet.Repository.UserRepository;
import com.example.GraplerEnhancemet.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository userRepo ;
	
	@GetMapping
	public List<User> getAllUser() {
		System.out.println();
		List<User> users =userRepo.findAll() ; 
		System.out.println(users);
		return users ;
	}

	
//	@GetMapping
//	public String  getAllUser() {
//		System.out.println(userRepo.findAll());
//		List<User> users =userRepo.findAll() ; 
//		System.out.println(users);
//		
//		return users.toString();
//	}
	
	@PostMapping
	public User addUser(@RequestBody User user) {
		//System.out.println(user);
		//System.out.println(user.getName());
		return userRepo.save(user);
	}
}
