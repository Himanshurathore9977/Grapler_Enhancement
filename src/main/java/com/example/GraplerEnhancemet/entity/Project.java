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
@Table(name = "project")
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
	   
	    @JsonBackReference
	    @ManyToOne
	    @JoinColumn(name = "created_by")
	    private User createdBy;
	   
//	   	@JsonBackReference 
//	    @ManyToOne
//	    @JoinColumn(name = "company_id")
//	    private Company company;
	   
	    @JsonBackReference 
	    @ManyToOne
	    private Workspace workspace;
	   

	    @JsonManagedReference
	    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Task> tasks;
	    
	    
	   	@JsonManagedReference
	    @OneToMany(mappedBy = "parentProject", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Folder> subfolders;
	    
	   @JsonManagedReference
	   @ManyToMany
	   private List<User>users;
	    


}
