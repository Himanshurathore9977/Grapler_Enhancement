package com.example.GraplerEnhancemet.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Entity
@Table(name = "users")
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
    @ManyToMany
    @JoinTable(
        name = "company_admins",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> adminCompanies;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "createdBy")
    private List<Company> companyCreated;
    
    @JsonManagedReference
    @ManyToMany(mappedBy = "users")
    private List<Company> companies;
    
    
//  @JsonManagedReference
//  @ManyToMany(cascade = CascadeType.ALL)
//  @JoinTable(
//      name = "user_task",
//      joinColumns = @JoinColumn(name = "user_id"),
//      inverseJoinColumns = @JoinColumn(name = "task_id")
//  )
//  private List<Task> tasks;
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n')
        .append("User{id=").append(id)
          .append(
        		  ", name='").append(name).append("'")
          .append(", adminCompanies=[");
        
        for (Company company : adminCompanies) {
            sb.append(company.getCompanyName()).append(", ");
        }
        
        if (!adminCompanies.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        
        sb.append("]}")
        ;
        
        return sb.toString();
    }
    
    
}

