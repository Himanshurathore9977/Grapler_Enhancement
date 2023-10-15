 package com.example.GraplerEnhancemet.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "projects")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
	public class Project {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long projectID;

	    @Column(name = "project_name", nullable = false)
	    private String projectName;
	    
	    @Column(name = "project_type")
	    private String type ;

	    @Column(name = "project_subtype")
	    private String  subtype; 
	   
	   
	    @ManyToOne
	    @JoinColumn(name = "created_by")
	    private User createdBy;

	    @ManyToOne
	    @JoinColumn(name = "company_id")
	    private Company company;

	    @ManyToOne
	    @JoinColumn(name = "workspace_id")
	    private Workspace workspace;

	    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Task> tasks;
	    		
	    @OneToMany(mappedBy = "parentProject", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Subfolder> subfolders;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "project_users",
	        joinColumns = @JoinColumn(name = "project_id"),
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	    private List<User> users;
	    


}
