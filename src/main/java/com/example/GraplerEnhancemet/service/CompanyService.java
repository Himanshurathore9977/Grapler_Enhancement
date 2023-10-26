package com.example.GraplerEnhancemet.service;

import com.example.GraplerEnhancemet.Repository.CompanyRepository;
import com.example.GraplerEnhancemet.Repository.WorkspaceRepository;
import com.example.GraplerEnhancemet.entity.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.GraplerEnhancemet.entity.Company;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.GraplerEnhancemet.dto.CompanyDTO;
import org.modelmapper.ModelMapper;


@Service
@Transactional
public class CompanyService {
    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;
    @Autowired
    private ModelMapper modelMapper; // Autowire ModelMapper

    public List<CompanyDTO> getAllCompanies() {
        try {
            List<Company> companies = companyRepository.findAll();
            logger.info("Retrieved all companies successfully.");
            // Convert Company objects to CompanyDTO using ModelMapper
            return companies.stream()
                    .map(company -> modelMapper.map(company, CompanyDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all companies", e);
            return null;
        }
    }

    public CompanyDTO getCompany(Long id) {
        try {
            Company company = companyRepository.findById(id).orElse(null);
            if (company != null) {
                CompanyDTO companyDTO = modelMapper.map(company, CompanyDTO.class);
                logger.info("Retrieved company details successfully.");
                return companyDTO;
            } else {
                logger.warn("Company not found with ID: " + id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error while getting company details with ID: " + id, e);
            return null;
        }
    }

    public CompanyDTO createCompany(CompanyDTO companyDTO) {
        try {
            Company company = modelMapper.map(companyDTO, Company.class);
            company.setCreationTime(LocalDateTime.now());
            Workspace workspace = new Workspace();
            workspace.setName("Default");
            workspace.setCreationTime(LocalDateTime.now());
            workspace.setCompany(company);
            workspaceRepository.save(workspace);
            Company createdCompany = companyRepository.save(company);
            CompanyDTO createdCompanyDTO = modelMapper.map(createdCompany, CompanyDTO.class);
            logger.info("Company created successfully: {}", createdCompanyDTO.getName());
            return createdCompanyDTO;
        } catch (Exception e) {
            logger.error("Error while creating company", e);
            return null;
        }
    }

    public CompanyDTO updateCompany(Long id, CompanyDTO updatedCompanyDTO) {
        try {
            Company existingCompany = companyRepository.findById(id).orElse(null);
            if (existingCompany != null) {
                if (updatedCompanyDTO.getName() != null) {
                    existingCompany.setName(updatedCompanyDTO.getName());
                }
                if (updatedCompanyDTO.getEmail() != null) {
                    existingCompany.setEmail(updatedCompanyDTO.getEmail());
                }
                if (updatedCompanyDTO.getLogo() != null) {
                    existingCompany.setLogo(updatedCompanyDTO.getLogo());
                }
                if (updatedCompanyDTO.getDescription() != null) {
                    existingCompany.setDescription(updatedCompanyDTO.getDescription());
                }
                if (updatedCompanyDTO.getStructureType() != null) {
                    existingCompany.setStructureType(updatedCompanyDTO.getStructureType());
                }
                if (updatedCompanyDTO.getContactNumber() != null) {
                    existingCompany.setContactNumber(updatedCompanyDTO.getContactNumber());
                }
                if (updatedCompanyDTO.getAddress() != null) {
                    existingCompany.setAddress(updatedCompanyDTO.getAddress());
                }

                // Save the updated entity
                Company savedCompany = companyRepository.save(existingCompany);

                CompanyDTO savedCompanyDTO = modelMapper.map(savedCompany, CompanyDTO.class);
                logger.info("Company updated successfully: {}", savedCompanyDTO.getName());
                return savedCompanyDTO;
            } else {
                logger.warn("Company not found with ID: " + id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error while updating company with ID: " + id, e);
            return null;
        }
    }

    public boolean deleteCompany(Long id) {
        try {
            Company existingCompany = companyRepository.findById(id).orElse(null);
            if (existingCompany != null) {
                workspaceRepository.deleteByCompany_Id(id);
                companyRepository.delete(existingCompany);
                logger.info("Company deleted successfully with ID: {}", id);
                return true;
            } else {
                logger.warn("Company not found with ID: " + id);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error while deleting company with ID: " + id, e);
            return false;
        }
    }
}

/*
@Service
@Transactional
public class CompanyService {
    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;


    public List<Company> getAllCompanies() {
        try {
            List<Company> companies = companyRepository.findAll();
            logger.info("Retrieved all companies successfully.");
            return companies;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all companies", e);
            return null;
        }
    }
    public Company getCompany(Long id) {
        try {
            logger.info("Retrieved company successfully.");
            return companyRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error while getting company with ID: " + id, e);
            return null;
        }
    }

    public Company createCompany(Company company) {
        try {
            company.setCreationTime(LocalDateTime.now());
            Workspace workspace = new Workspace();
            workspace.setName("Default");
            workspace.setCreationTime(LocalDateTime.now());
            workspace.setCompany(company);
            workspaceRepository.save(workspace);
            Company createdCompany = companyRepository.save(company);
            logger.info("Company created successfully: {}", createdCompany.getName());
            return createdCompany;
        } catch (Exception e) {
            logger.error("Error while creating company", e);
            return null;
        }
    }

    public Company updateCompany(Long id, Company updatedCompany) {
        try {
            Company existingCompany = getCompany(id);
            if (existingCompany != null) {
                updatedCompany.setId(existingCompany.getId());
                updatedCompany.setCreationTime(existingCompany.getCreationTime());
                Company savedCompany = companyRepository.save(updatedCompany);
                logger.info("Company updated successfully: {}", savedCompany.getName());
                return savedCompany;
            }
            return null;
        } catch (Exception e) {
            logger.error("Error while updating company with ID: " + id, e);
            return null;
        }
    }

    public boolean deleteCompany(Long id) {
        try {
            Company existingCompany = getCompany(id);
            if (existingCompany != null) {
                workspaceRepository.deleteByCompany_Id(id);
                companyRepository.delete(existingCompany);
                logger.info("Company deleted successfully with ID: {}", id);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error while deleting company with ID: " + id, e);
            return false;
        }
    }
}
*/