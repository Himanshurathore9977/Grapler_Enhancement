package com.example.GraplerEnhancemet.service;

import com.example.GraplerEnhancemet.Repository.ProjectRepository;
import com.example.GraplerEnhancemet.Repository.WorkspaceRepository;
import com.example.GraplerEnhancemet.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private WorkspaceRepository workspaceRepository;

    public List<Project> getAllProjects(Long workspaceId) {
        try {
            List<Project> projects = projectRepository.findAllByWorkspace_Id(workspaceId);
            logger.info("Retrieved all projects successfully.");
            return projects;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all projects", e);
            return null;
        }
    }

    public Project getProject(Long workspaceId, Long projectId) {
        try {
            Project project = projectRepository.findByWorkspace_IdAndId(workspaceId, projectId);
            logger.info("Retrieved project successfully.");
            return project;
        } catch (Exception e) {
            logger.error("Error while getting project with ID: " + projectId, e);
            return null;
        }
    }

    public Project createProject(Long workspaceId, Project project) {
        try {
            project.setWorkspace(workspaceRepository.findById(workspaceId).orElse(null));
            project.setCreationTime(LocalDateTime.now());
            Project createdProject = projectRepository.save(project);
            logger.info("Project created successfully: {}", createdProject.getName());
            return createdProject;
        } catch (Exception e) {
            logger.error("Error while creating project", e);
            return null;
        }
    }

    public Project updateProject(Long workspaceId, Long projectId, Project updatedProject) {
        try {
            Project existingProject = getProject(workspaceId, projectId);
            if (existingProject != null) {
                updatedProject.setId(existingProject.getId());
                updatedProject.setCreationTime(existingProject.getCreationTime());
                updatedProject.setWorkspace(existingProject.getWorkspace()); // Maintain the same workspace.
                Project savedProject = projectRepository.save(updatedProject);
                logger.info("Project updated successfully: {}", savedProject.getName());
                return savedProject;
            }
            return null;
        } catch (Exception e) {
            logger.error("Error while updating project with ID: " + projectId, e);
            return null;
        }
    }
    public boolean deleteProject(Long workspaceId, Long projectId) {
        try {
            Project existingProject = getProject(workspaceId, projectId);
            if (existingProject != null) {
                projectRepository.delete(existingProject);
                logger.info("Project deleted successfully with ID: {}", projectId);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error while deleting project with ID: " + projectId, e);//e -> print stack trace
            return false;
        }
    }
}

