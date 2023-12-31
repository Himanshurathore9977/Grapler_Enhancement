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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Company {
	@Id                                                                
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id ;
	
	
	@Column( nullable = false)
    private String companyName;

	
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
	
	@JsonManagedReference
	@OneToMany(mappedBy = "company" ,  cascade = CascadeType.ALL)
	private List<CompanyUserRole> userRole ; 
	
	  
	@JsonManagedReference 
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Workspace> workspaces;
	


	
	
	
}
