package com.example.GraplerEnhancemet.entity;

import java.sql.Blob;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Lob
    private byte[] profile;
    
    
    

    @JsonIgnore
    @OneToMany(mappedBy = "user" ,   cascade = CascadeType.ALL , fetch = FetchType.EAGER  )
    private List<CompanyUserRole> company ; 
   
   
	@ManyToMany(mappedBy = "users" ,  fetch = FetchType.EAGER)
	private List<Project> projects;


	@OneToMany(mappedBy = "taskCreator" , fetch = FetchType.EAGER )
	private List<Task> taskCreated;


	@JsonIgnore
    @ManyToMany
    private List<Task> assignedTask;

	
	@JsonIgnore
    @OneToMany(mappedBy = "accountableAssignee" , fetch = FetchType.EAGER  )
    private List<Task> accountableAssigned ; 

}

