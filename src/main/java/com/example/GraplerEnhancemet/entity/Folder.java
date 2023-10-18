package com.example.GraplerEnhancemet.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "folder")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    
    @Column(name = "folderName")
    private String folderName;
    
    @ManyToOne(cascade = CascadeType.ALL)
    //@JsonIdentityReference(alwaysAsId = true) // Serialize references as IDs
    private Project parentProject;
    
    //@JsonIdentityReference(alwaysAsId = true) // Serialize references as IDs
    @ManyToOne(cascade = CascadeType.ALL)
    private Folder parentfolder;

    //@JsonManagedReference
    @OneToMany(mappedBy = "parentfolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> subfolders;

    //@JsonManagedReference
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
