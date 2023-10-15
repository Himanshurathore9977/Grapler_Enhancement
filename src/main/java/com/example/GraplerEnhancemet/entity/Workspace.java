package com.example.GraplerEnhancemet.entity;

import java.util.List;

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
@Table(name = "workspace")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Workspace {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "workspaceID",  nullable = false )
	 private Long workspaceID ;
	 
	 @Column(name = "workspaceName", nullable = false)
	 private String workspaceName ;
	 
	 @ManyToOne
	 @JoinColumn(name = "company_id")
	 private Company company;
	 
	 @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Project> projects;
	 
	 @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Task> tasks;  
	 
	 
	 
	
}
