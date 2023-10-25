package com.example.GraplerEnhancemet.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.GraplerEnhancemet.entity.Project;
import com.example.GraplerEnhancemet.service.ProjectService;
import com.example.GraplerEnhancemet.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/workspaces/{workspaceId}/projects")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Project>>> getAllProjects(@PathVariable Long workspaceId) {
        try {
            List<Project> projects = projectService.getAllProjects(workspaceId);
            logger.info("All projects successfully retrieved");
            return ResponseEntity.ok(new ApiResponse<>(true, projects, "All projects retrieved successfully"));
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all projects", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ApiResponse<Project>> getProject(@PathVariable Long workspaceId, @PathVariable Long projectId) {
        try {
            Project project = projectService.getProject(workspaceId, projectId);
            if (project != null) {
                logger.info("Project retrieved successfully: {}", project.getName());
                return ResponseEntity.ok(new ApiResponse<>(true, project, "Project retrieved successfully"));
            } else {
                logger.warn("Project not found with ID: {}", projectId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Project not found"));
            }
        } catch (Exception e) {
            logger.error("Internal Server Error while retrieving project with ID: " + projectId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Project>> createProject(@PathVariable Long workspaceId, @RequestBody Project project) {
        try {
            Project createdProject = projectService.createProject(workspaceId, project);
            logger.info("Project created successfully: {}", createdProject.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, createdProject, "Project created successfully"));
        } catch (Exception e) {
            logger.error("Internal Server Error while creating a project", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
        }
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ApiResponse<Project>> updateProject(@PathVariable Long workspaceId, @PathVariable Long projectId, @RequestBody Project project) {
        try {
            Project updatedProject = projectService.updateProject(workspaceId, projectId, project);
            if (updatedProject != null) {
                logger.info("Project updated successfully: {}", updatedProject.getName());
                return ResponseEntity.ok(new ApiResponse<>(true, updatedProject, "Project updated successfully"));
            } else {
                logger.warn("Project not found with ID: {}", projectId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Project not found"));
            }
        } catch (Exception e) {
            logger.error("Internal Server Error while updating project with ID: " + projectId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
        }
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse<String>> deleteProject(@PathVariable Long workspaceId, @PathVariable Long projectId) {
        try {
            boolean deleted = projectService.deleteProject(workspaceId, projectId);
            if (deleted) {
                logger.info("Project deleted successfully with ID: {}", projectId);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Project not found with ID: {}", projectId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Project not found"));
            }
        } catch (Exception e) {
            logger.error("Internal Server Error while deleting project with ID: " + projectId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
        }
    }
}
