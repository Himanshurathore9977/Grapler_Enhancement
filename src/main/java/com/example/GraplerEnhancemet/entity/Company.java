package com.example.GraplerEnhancemet.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "companyID"
)

public class Company {
	@Id                                                                
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "companyID",  nullable = false )
	private Long companyID ;
	
	
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
	
	
	//company admins 
	@JsonManagedReference // This side is the "reverse" part of the relationship
	@ManyToMany(mappedBy = "adminCompanies")
    private List<User> companyAdmins;
    
	
	//company creator 
	@JsonBackReference
	@ManyToOne
    private User createdBy ;
		
	 
//	//all workspaces 
//	@JsonManagedReference 
//	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Workspace> workspaces;
//	
//	
	// all users in company 
	@JsonManagedReference 
	@ManyToMany
    @JoinTable(
        name = "company_user",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> users; 
}
