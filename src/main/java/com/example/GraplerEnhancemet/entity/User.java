package com.example.GraplerEnhancemet.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile")
    private String profile;
    
 
    @JsonManagedReference
    @OneToMany(mappedBy = "user" ,   cascade = CascadeType.ALL , orphanRemoval = true  )
    private List<CompanyUserRole> company ; 
   
    
    // projects 
//  @JsonManagedReference
//	@ManyToMany
//	private List<Project> projects;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "taskCreator" )
    private List<Task> taskCreated;
//    
    @JsonManagedReference
    @ManyToMany
    private List<Task> assignedTask;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "accountableAssignee")
    private List<Task> accountableAssigned ; 
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n')
        .append("User{id=").append(id)
          .append(
        		  ", name='").append(name).append("'") ; 
        sb.append("]}") ;
        return sb.toString();
    }
    
    
}

