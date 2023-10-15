package com.example.GraplerEnhancemet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraplerEnhancemet.Repository.CompanyRepository;
import com.example.GraplerEnhancemet.entity.Company;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyRepository companyRepo ; 
	
	
	
	@PostMapping
	public Company addOwner(@RequestBody Company company) {
		System.out.println(company.getCompanyName());
		return companyRepo.save(company);
	}

}
