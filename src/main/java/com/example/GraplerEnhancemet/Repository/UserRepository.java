package com.example.GraplerEnhancemet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GraplerEnhancemet.entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Long>   {

}
