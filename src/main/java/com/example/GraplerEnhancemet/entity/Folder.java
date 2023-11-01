package com.example.GraplerEnhancemet.entity;

import java.util.List;

import com.example.GraplerEnhancemet.entity.enums.FolderType;
import com.example.GraplerEnhancemet.entity.enums.StructureType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name should not exceed 255 characters")
    private String name;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "FolderType type must be provided")
    private FolderType FolderType;

    @JsonIgnore
    //@JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private Project parentProject;

    @JsonIgnore
//    @JsonIdentityReference(alwaysAsId = true)//self Mapping
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.EAGER)
    private Folder parentfolder;

//    @JsonIgnore
    @OneToMany(mappedBy = "parentfolder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Folder> subfolders;

    @JsonIgnore
//    @JsonManagedReference
    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} )
    private List<Task> tasks;
}