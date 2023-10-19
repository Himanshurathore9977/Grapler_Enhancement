package com.example.GraplerEnhancemet.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraplerEnhancemet.Repository.CompanyRepository;
import com.example.GraplerEnhancemet.Repository.UserRepository;
import com.example.GraplerEnhancemet.Repository.WorkspaceRepository;
import com.example.GraplerEnhancemet.entity.Company;
import com.example.GraplerEnhancemet.entity.Workspace;

@RestController
@RequestMapping("/companies")
@EnableTransactionManagement
public class CompanyController {
	@Autowired
	CompanyRepository companyRepo ; 
	@Autowired
	UserRepository userRepo ; 
	@Autowired
	WorkspaceRepository workspaceRepo ; 
	
	
	@GetMapping
	public List<Company> getAllUser() {
		return companyRepo.findAll() ;
	}
	
	@GetMapping("/{id}") // GET /users/1
	public Company getCompany(@PathVariable("id") Long id) {
		Optional<Company> optional = companyRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;
	}
	@PostMapping
	public Company addOwner(@RequestBody Company company) {
		System.out.println("Company is created ");
		company.setCreationTime( LocalDateTime.now());
		
		Workspace workspace = new Workspace () ;
		workspace.setCompany(company);
		workspace.setName("Default");
		
		workspaceRepo.save(workspace) ; 
		
		System.out.println(company.getCompanyName());
		return companyRepo.save(company);
	}

}
