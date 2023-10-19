package com.example.GraplerEnhancemet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GraplerEnhancemet.entity.Workspace;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long>{

}
