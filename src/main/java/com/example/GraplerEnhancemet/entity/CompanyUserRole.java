package com.example.GraplerEnhancemet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "companyUserRole")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CompanyUserRole {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	
	 @Enumerated(EnumType.STRING)
	 private RoleEnum role;
	 
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	private User user ; 
	
//	@JsonBackReference
	@ManyToOne
	private Company company ; 
	
	
}
