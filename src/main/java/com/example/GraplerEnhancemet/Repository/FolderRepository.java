package com.example.GraplerEnhancemet.Repository;

import com.example.GraplerEnhancemet.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
  List<Folder> findAllByParentProject_Id(Long projectId);
    Folder findByParentProject_IdAndID(Long projectId, Long folderId);
  void deleteByParentProject_Id(Long ProjectId);
}
