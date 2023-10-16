//package com.example.GraplerEnhancemet.entity;
//
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonIdentityReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "subfolder")
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//public class Subfolder {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long subfolderID;
//
//    @Column(name = "name", nullable = false)
//    private String subFoldername;
//    
//    @JsonIdentityReference(alwaysAsId = true) // Serialize references as IDs
//    @ManyToOne
//    @JoinColumn(name = "parent_id")
//    private Subfolder parentSubfolder;
//
//    @ManyToOne
//    @JsonIdentityReference(alwaysAsId = true) // Serialize references as IDs
//    @JoinColumn(name = "project_id")
//    private Project parentProject;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "parentSubfolder", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Subfolder> subfolders;
//
//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Task> tasks;
//}
