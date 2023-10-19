package com.example.GraplerEnhancemet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraplerEnhancemet.Repository.CompanyRepository;
import com.example.GraplerEnhancemet.Repository.UserRepository;
import com.example.GraplerEnhancemet.entity.Company;
import com.example.GraplerEnhancemet.entity.CompanyUserRole;
import com.example.GraplerEnhancemet.entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserRepository userRepo ;
	
	@Autowired
	CompanyRepository companyRepo ; 
	
	@GetMapping
	public List<User> getAllUser() {
		int id = 1 ; 
		List <Company> companies = companyRepo.findAll() ; 
		List<User> user = userRepo.findAll() ; 
//		List<CompanyUserRole> userRole = new ArrayList<CompanyUserRole>() ; 
//
//		List<User> user = new ArrayList<User>() ; 
//		userRole = companies.stream().map(company -> company.getUserRole()).collect(Collectors.toList()); 
//  		user =  userRole.stream().map(role -> role.getUser()).collect(Collectors.toList()) ;
//		System.out.println(user);
  		//List<CompanyUserRole> users = 
		//System.out.println(users);
		return user  ;
	}

	
	@PostMapping
	public User addUser(@RequestBody User user) {
		//System.out.println(user);
		//System.out.println(user.getName());
		return userRepo.save(user);
	}
}
