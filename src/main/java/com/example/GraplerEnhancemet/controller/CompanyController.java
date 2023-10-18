package com.example.GraplerEnhancemet.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraplerEnhancemet.Repository.CompanyRepository;
import com.example.GraplerEnhancemet.Repository.UserRepository;
import com.example.GraplerEnhancemet.entity.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyRepository companyRepo ; 
	
	@Autowired
	UserRepository userRepo ; 
	@GetMapping("/")
	public List<Company> getAllUser() {
		return companyRepo.findAll() ;
	}
	
	@PostMapping
	public Company addOwner(@RequestBody Company company) {
		System.out.println("Company is created ");
		//set creation time 
		company.setCreationTime( LocalDateTime.now());
		
		System.out.println(company.getCompanyName());
		return companyRepo.save(company);
	}

}
