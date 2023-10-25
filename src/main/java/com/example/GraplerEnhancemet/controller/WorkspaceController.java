package com.example.GraplerEnhancemet.controller;

import com.example.GraplerEnhancemet.service.WorkspaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import com.example.GraplerEnhancemet.entity.Workspace;
import com.example.GraplerEnhancemet.util.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/workspaces")
@EnableTransactionManagement
public class WorkspaceController {
	private static final Logger logger = LoggerFactory.getLogger(WorkspaceController.class);

	@Autowired
	private WorkspaceService workspaceService;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Workspace>>> getAllWorkspaces(@PathVariable Long companyId) {
		try {
			List<Workspace> workspaces = workspaceService.getAllWorkspaces(companyId);
			logger.info("All workspaces for company {} retrieved successfully", companyId);
			return ResponseEntity.ok(new ApiResponse<>(true, workspaces, "All workspaces retrieved successfully"));
		} catch (Exception e) {
			logger.error("Error occurred while retrieving all workspaces for company " + companyId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@GetMapping("/{workspaceId}")
	public ResponseEntity<ApiResponse<Workspace>> getWorkspace(@PathVariable Long companyId, @PathVariable Long workspaceId) {
		try {
			Workspace workspace = workspaceService.getWorkspace(companyId, workspaceId);
			if (workspace != null) {
				logger.info("Workspace retrieved successfully: {}", workspace.getName());
				return ResponseEntity.ok(new ApiResponse<>(true, workspace, "Workspace retrieved successfully"));
			} else {
				logger.warn("Workspace not found with ID: {}", workspaceId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Workspace not found"));
			}
		} catch (Exception e) {
			logger.error("Internal Server Error while retrieving workspace with ID: " + workspaceId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Workspace>> createWorkspace(@PathVariable Long companyId, @RequestBody Workspace workspace) {
		try {
			Workspace createdWorkspace = workspaceService.createWorkspace(companyId, workspace);
			logger.info("Workspace created successfully: {}", createdWorkspace.getName());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, createdWorkspace, "Workspace created successfully"));
		} catch (Exception e) {
			logger.error("Internal Server Error while creating workspace", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@PutMapping("/{workspaceId}")
	public ResponseEntity<ApiResponse<Workspace>> updateWorkspace(@PathVariable Long companyId, @PathVariable Long workspaceId, @RequestBody Workspace workspace) {
		try {
			Workspace updatedWorkspace = workspaceService.updateWorkspace(companyId, workspaceId, workspace);
			if (updatedWorkspace != null) {
				logger.info("Workspace updated successfully: {}", updatedWorkspace.getName());
				return ResponseEntity.ok(new ApiResponse<>(true, updatedWorkspace, "Workspace updated successfully"));
			} else {
				logger.warn("Workspace not found with ID: {}", workspaceId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Workspace not found"));
			}
		} catch (Exception e) {
			logger.error("Internal Server Error while updating workspace with ID: " + workspaceId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}

	@DeleteMapping("/{workspaceId}")
	public ResponseEntity<ApiResponse<String>> deleteWorkspace(@PathVariable Long companyId, @PathVariable Long workspaceId) {
		try {
			boolean deleted = workspaceService.deleteWorkspace(companyId, workspaceId);
			if (deleted) {
				logger.info("Workspace deleted successfully with ID: {}", workspaceId);
				return ResponseEntity.noContent().build();
			} else {
				logger.warn("Workspace not found with ID: {}", workspaceId);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Workspace not found"));
			}
		} catch (Exception e) {
			logger.error("Internal Server Error while deleting workspace with ID: " + workspaceId, e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, "Internal Server Error"));
		}
	}
}
