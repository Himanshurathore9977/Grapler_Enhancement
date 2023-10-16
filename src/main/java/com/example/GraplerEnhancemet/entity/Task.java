//package com.example.GraplerEnhancemet.entity;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIdentityReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//@Entity
//@Table(name = "tasks")
//@Data
//public class Task {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "description")
//    private String description;
//
//    @JsonManagedReference
//    @ManyToMany(mappedBy = "tasks")
//    private List<User> assignee;
//
//    @ManyToOne
//    @JoinColumn(name = "subfolder_id")
//    private Subfolder subfolder;
//
//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project;
//
//    @ManyToOne
//    @JoinColumn(name = "workspace_id")
//    private Workspace workspace;
//
//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private Company company;
//
//    @Column(name = "status")
//    private String status;
//
//    public Task() {
//        // Generate or initialize fields here, if needed
//    }
//}
