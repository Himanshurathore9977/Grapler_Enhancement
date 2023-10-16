//package com.example.GraplerEnhancemet.entity;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//
//
//@Entity
//@Table(name = "user_roles")
//@Data
//public class UserRole {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "role_name", nullable = false)
//    private String roleName;
//
//    // Define any additional properties for roles as needed
//    @JsonManagedReference
//    @ManyToMany(mappedBy = "roles")
//    private List<User> users;
//
//    public UserRole() {
//        // Generate or initialize fields here, if needed
//    }
//}
