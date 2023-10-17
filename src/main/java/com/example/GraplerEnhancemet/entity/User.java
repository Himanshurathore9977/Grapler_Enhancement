package com.example.GraplerEnhancemet.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @OneToMany
    private List <Role> roles ;
    
       
    @JsonManagedReference
    @OneToMany(mappedBy = "createdBy")
    private List<Company> companyCreated;
    
    
    // projects 
    @JsonManagedReference
	@ManyToMany
	private List<Project> projects;
    
    
//    @JsonManagedReference
//    @ManyToMany(mappedBy = "user")
//    private List<Company> companies;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "taskCreator", cascade = CascadeType.ALL)
    private List<Task> taskCreated;
    
    @JsonManagedReference
    @ManyToMany( cascade = CascadeType.ALL)
    private List<Task> assignedTask;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "accountableAssignee", cascade = CascadeType.ALL)
    private List<Task> accountableAssigned ; 
    
  @JsonManagedReference
  @ManyToMany(cascade = CascadeType.ALL)
  private List<Company> companies ;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n')
        .append("User{id=").append(id)
          .append(
        		  ", name='").append(name).append("'") ; 
         // .append(", adminCompanies=[");
        
        
        sb.append("]}") ;
        
        return sb.toString();
    }
    
    
}

