package com.example.GraplerEnhancemet.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private String status; 
    
    
    @ManyToOne(cascade = CascadeType.ALL )
    private Folder folder;
    
    
    @ManyToOne
    private User taskCreator;
    
    
    @ManyToMany(mappedBy = "assignedTask" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<User> assignedTo;

    
    @ManyToOne
    private User accountableAssignee ; 

    
    	@ManyToOne(cascade = CascadeType.ALL)
    	private Project project;
//
  

//
   

}
