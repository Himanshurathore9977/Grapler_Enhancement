package com.example.GraplerEnhancemet.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
//@JsonIdentityInfo(
//	    generator = ObjectIdGenerators.PropertyGenerator.class,
//	    property = "companyID"
//)

public class Company {
	@Id                                                                
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id ;
	
	
	@Column(name = "companyName", nullable = false)
    private String companyName;

	
	@Column(name = "email", nullable = false)
    private String email; 
	
	
	@Lob
	@Column(name = "logoThumbnail")
	private byte[] logoThumbnail;
	 
	@Column(name = "description")
	private String description; 
	
	//@Enumerated(EnumType.STRING)
    @Column(name = "structure_type")
    private String  structureType;
	
	@Column(name = "contact_number")
    private String contactNumber; 
		
	@Column(name = "address")
    private String address; 
	
	@Column(name = "creationTime")
	private LocalDateTime creationTime ; 
	
	//@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	private List<CompanyUserRole> user ; 
		
	 
	//all workspaces 
	@JsonManagedReference 
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<Workspace> workspaces;

	
	
	
}
