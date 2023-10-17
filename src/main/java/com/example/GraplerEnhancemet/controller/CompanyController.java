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
	@GetMapping
	public List<Company> getAllUser() {
		System.out.println(companyRepo.findAll());
		List<Company> compnay =companyRepo.findAll() ; 
		System.out.println(compnay);
		return compnay ;
	}
	
//	@PostMapping
//	public Company addOwner(@RequestBody Company company) {
//		System.out.println("Company is created ");
//		//set creation time 
//		//company.setCreationTime( LocalDateTime.now());
//		
////		//CREATED BY 
////		User createdBY = company.getCreatedBy() ; 
////		userRepo.save(createdBY) ;
//		
////		//ADMINLIST 
////		List<User> AdminList = company.getCompanyAdmins() ; 
////		//AdminList.forEach((admin) -> admin.setAdminCompanies());
////		userRepo.saveAll(AdminList) ; 
//		
//		
////		//USER
////		List<User> userList = company.getUsers() ; 
////		userRepo.saveAll(userList) ; 
//
//		System.out.println(company.getCompanyName());
//		return companyRepo.save(company);
//	}

}
