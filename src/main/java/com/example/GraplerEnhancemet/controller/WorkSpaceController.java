package com.example.GraplerEnhancemet.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GraplerEnhancemet.Repository.CompanyRepository;
import com.example.GraplerEnhancemet.Repository.WorkspaceRepository;
import com.example.GraplerEnhancemet.entity.Company;
import com.example.GraplerEnhancemet.entity.Workspace;

@RestController
@RequestMapping("workspaces")
public class WorkSpaceController {
	@Autowired
	WorkspaceRepository workspaceRepo ;
	
	@Autowired
	CompanyRepository companyRepo ; 
	
	@GetMapping("/{companyID}")
	public List<Workspace> getWorkspacesofCompany(@PathVariable("companyID") Long companyID) {
		Optional<Company> company =companyRepo.findById(companyID) ; 
		if(company.isPresent()) {
			List<Workspace> workspace =workspaceRepo.findAll() ; 
			return workspace.stream().filter(z -> z.getCompany().getId() == companyID).collect(Collectors.toList()) ; 
		}
		else {
			return null ; 
		}
	}
//	@GetMapping("/{companyID}/{workspaceID}")
	
	
	
}
