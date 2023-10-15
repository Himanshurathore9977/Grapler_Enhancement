package com.example.GraplerEnhancemet.entity;

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

import java.util.List;

@Entity
@Table(name = "subfolder")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subfolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subfolderID;

    @Column(name = "name", nullable = false)
    private String subFoldername;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Subfolder parentSubfolder;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project parentProject;

    @OneToMany(mappedBy = "parentSubfolder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subfolder> subfolders;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;
}
