package com.example.GraplerEnhancemet.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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
	private Date creationTime ; 
	
	@ManyToMany
    @JoinTable(
        name = "company_admins",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> companyAdmins;
	
	@ManyToOne
    private User createdBy ;
		
	// Relationships 
	//all workspaces 
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Workspace> workspaces;
	
	//list of all user 
	//@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	
	
	@ManyToMany
    @JoinTable(
        name = "company_user",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	private List<User> users; 
}
