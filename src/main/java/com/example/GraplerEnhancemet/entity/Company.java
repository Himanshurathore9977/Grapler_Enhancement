package com.example.GraplerEnhancemet.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.GraplerEnhancemet.entity.enums.StructureType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@NotBlank(message = "Name is required")
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Column(nullable = false)
	private String email;

	@Lob
	private byte[] logo;

	@Size(max = 255, message = "Description should not exceed 255 characters")
	private String description;

	@Enumerated(EnumType.STRING)
	private StructureType structureType;

	@Pattern(regexp = "\\d{10}", message = "Invalid contact number")
	private String contactNumber;

	@NotBlank(message = "Address is required")
	private String address;

	@NotNull(message = "Creation time must be provided")
	private LocalDateTime creationTime;
/*
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id ;


	@Column( nullable = false)
    private String name;


	@Column(nullable = false)
    private String email;

	@Lob
	private byte[] logo ;

	private String description;

	@Enumerated(EnumType.STRING)
    private StructureType  structureType;

    private String contactNumber;

    private String address;

	private LocalDateTime creationTime ;
*/
	//This indicates that these are the "child" entities, and their serialization should be controlled by
	// the parent entity
	@JsonManagedReference
	//When company update refresh create then this also refresh
	@OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<CompanyUserRole> userRole ;
	
	  
	@JsonManagedReference
	@OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Workspace> workspaces;


}
