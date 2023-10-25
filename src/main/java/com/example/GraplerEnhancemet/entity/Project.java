 package com.example.GraplerEnhancemet.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
	public class Project {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id ;

	    @Column(nullable = false)
	    private String name;
	    
	    private String type ;

	    private String  subType;

		private LocalDateTime creationTime ;


		@JsonBackReference
		@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.EAGER)
		private Workspace workspace;

	    @JsonManagedReference
		@OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	    private List<Task> tasks;

//		@JsonManagedReference
		@JsonIgnore
	    @OneToMany(mappedBy = "parentProject", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	    private List<Folder> subFolders;

//	   @JsonBackReference
		@JsonIgnore
		@ManyToMany(fetch = FetchType.EAGER)
		private List<User> users;


}
