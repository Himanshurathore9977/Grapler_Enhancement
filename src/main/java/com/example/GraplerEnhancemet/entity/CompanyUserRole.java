package com.example.GraplerEnhancemet.entity;

import com.example.GraplerEnhancemet.entity.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
	 
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User user ; 
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER) 
	private Company company ; 
	
	
}
