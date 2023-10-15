package com.example.GraplerEnhancemet.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GraplerEnhancemet.entity.Company;


@Repository
public interface CompanyRepository extends  JpaRepository<Company, Long>  {

}