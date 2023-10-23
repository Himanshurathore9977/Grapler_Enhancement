package com.example.GraplerEnhancemet.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@JsonIdentityInfo(
	    generator = ObjectIdGenerators.PropertyGenerator.class,
	    property = "id"
)

public class Workspace {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column( nullable = false )
	 private Long id ;
	 
	 @Column( nullable = false)
	 private String name ;
	 
	 @JsonBackReference
	 @ManyToOne(cascade = CascadeType.ALL)
	 private Company company;
	 
 	 @JsonManagedReference
	 @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	 private List<Project> projects;
  

	 
	 
	
}
