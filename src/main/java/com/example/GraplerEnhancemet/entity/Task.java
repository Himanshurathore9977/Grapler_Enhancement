package com.example.GraplerEnhancemet.entity;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    
    @JsonBackReference
    @ManyToOne
    //@JoinColumn(name = "folder_id")
    private Folder folder;
    
    
    @JsonBackReference
    @ManyToOne
    private User taskCreator;
    
    
//    @JsonManagedReference
//    @ManyToMany(mappedBy = "assignedTask")
//    private List<User> assignedTo;
//    
    @JsonBackReference
    @ManyToOne
    private User accountableAssignee ; 
//
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;
//
//   @ManyToOne 
//   @JoinColumn(name = "workspace_id")
//   private Workspace workspace;
////
//   @ManyToOne
//   @JoinColumn(name = "company_id")
//   private Company company;
//
   

}
