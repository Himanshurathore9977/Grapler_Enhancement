package com.example.GraplerEnhancemet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import com.example.GraplerEnhancemet.entity.Company;
import com.example.GraplerEnhancemet.service.CompanyService;
import com.example.GraplerEnhancemet.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/companies")
@EnableTransactionManagement
public class CompanyController {
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Company>>> getAllCompanies() {
		try {
			List<Company> companies = companyService.getAllCompanies();
			logger.error("All companies successfully retrieved");
			return ResponseEntity.ok(new ApiResponse<>(true, companies, "All companies retrieved successfully"));
		} catch (Exception e) {
			logger.error("Error occurred while retrieving all companies", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Company>> getCompany(@PathVariable Long id) {
		try {
			Company company = companyService.getCompany(id);
			if (company != null) {
				logger.info("Company retrieved successfully: {}", company.getName());
				return ResponseEntity.ok(new ApiResponse<>(true, company, "Company retrieved successfully"));
			} else {
				logger.warn("Company not found with ID: {}", id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Company not found"));
			}
		} catch (Exception e) {
			logger.error("Internal Server Error while retrieving company with ID: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Company>> createCompany(@RequestBody Company company) {
		try {
			Company createdCompany = companyService.createCompany(company);
			logger.info("Company created successfully: {}", createdCompany.getName());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, createdCompany, "Company created successfully"));
		} catch (Exception e) {
			logger.error("Internal Server Error while creating company", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Company>> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		try {
			Company updatedCompany = companyService.updateCompany(id, company);
			if (updatedCompany != null) {
				logger.info("Company updated successfully: {}", updatedCompany.getName());
				return ResponseEntity.ok(new ApiResponse<>(true, updatedCompany, "Company updated successfully"));
			} else {
				logger.warn("Company not found with ID: {}", id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Company not found"));
			}
		} catch (Exception e) {
			logger.error("Internal Server Error while updating company with ID: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteCompany(@PathVariable Long id) {
		try {
			boolean deleted = companyService.deleteCompany(id);
			if (deleted) {
				logger.info("Company deleted successfully with ID: {}", id);
				return ResponseEntity.noContent().build();
			} else {
				logger.warn("Company not found with ID: {}", id);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Company not found"));
			}
		} catch (Exception e) {
			logger.error("Internal Server Error while deleting company with ID: " + id, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}
}
