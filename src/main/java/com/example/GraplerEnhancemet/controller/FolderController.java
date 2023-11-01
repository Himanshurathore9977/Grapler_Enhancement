package com.example.GraplerEnhancemet.controller;

import com.example.GraplerEnhancemet.custom_exception.ParentNotFoundException;
import com.example.GraplerEnhancemet.entity.Folder;
import com.example.GraplerEnhancemet.service.FolderService;
import com.example.GraplerEnhancemet.util.ApiResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/projects")
@EnableTransactionManagement
@Validated
@CrossOrigin(origins = "http://localhost:3000")
public class FolderController {
    private static final Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderService folderService;

    @GetMapping("/{projectId}/folders")
    public ResponseEntity<ApiResponse<List<Folder>>> getAllFolders(@PathVariable Long projectId) {
        try {
            List<Folder> folders = folderService.getAllFolders(projectId);
            if (!folders.isEmpty()) {
                logger.info("All folders successfully retrieved for Project ID: {}", projectId);
                return ResponseEntity.ok(new ApiResponse<>(true, folders, "All folders retrieved successfully"));
            } else {
                logger.warn("No folders found for Project ID: {}", projectId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "No folders found"));
            }
        } catch (ParentNotFoundException ex) {
            logger.error("Parent not found : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, ex.getMessage()));
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all folders", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }

    @GetMapping("/folders/{folderId}")
    public ResponseEntity<ApiResponse<Folder>> getFolder(@PathVariable Long folderId) {
        try {
            Folder folder = folderService.getFolder(folderId);
            if (folder != null) {
                logger.info("Folder retrieved successfully with ID: {}", folderId);
                return ResponseEntity.ok(new ApiResponse<>(true, folder, "Folder retrieved successfully"));
            } else {
                logger.warn("Folder not found with Folder ID: {}", folderId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Folder not found"));
            }
        } catch (Exception e) {
            logger.error("Internal Server Error while retrieving folder with ID: " + folderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }

    @PostMapping("/{projectId}/folders")
//    To create Root Folder /projects/123/folders
//    To create Sub Folder /projects/123/folders?parentFolderId=1
    public ResponseEntity<ApiResponse<Folder>> createFolder(@PathVariable Long projectId, @RequestParam(value = "parentFolderId", defaultValue = "-1") Long parentFolderId,@Valid @RequestBody Folder folder) {
        try {
            Folder createdFolder = folderService.createFolder(projectId, parentFolderId, folder);
                logger.info("Folder created successfully with ID: {}", createdFolder.getID());
                return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, createdFolder, "Folder created successfully"));
        } catch (ParentNotFoundException ex) {
            logger.error("Parent not found : {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, ex.getMessage()));
        } catch (Exception e) {
            logger.error("Internal Server Error while creating folder", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }

    @PutMapping("/folders/{folderId}")
    public ResponseEntity<ApiResponse<Folder>> updateFolder( @PathVariable Long folderId,@Valid @RequestBody Folder folder) {
        try {
            Folder updatedFolder = folderService.updateFolder(folderId, folder);
            if (updatedFolder != null) {
                logger.info("Folder updated successfully with ID: {}", folderId);
                return ResponseEntity.ok(new ApiResponse<>(true, updatedFolder, "Folder updated successfully"));
            } else {
                logger.warn("Folder not found with Folder ID: {}", folderId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Folder not found"));
            }
        }  catch (Exception e) {
            logger.error("Internal Server Error while updating folder with ID: " + folderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }

    @DeleteMapping("/folders/{folderId}")
    public ResponseEntity<ApiResponse<?>> deleteFolder(@PathVariable Long folderId) {
        try {
            boolean deleted = folderService.deleteFolder(folderId);
            if (deleted) {
                logger.info("Folder deleted successfully with ID: {}", folderId);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Folder not found with Folder ID: {}", folderId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "Folder not found"));
            }
        } catch (Exception e) {
            logger.error("Internal Server Error while deleting folder with ID: " + folderId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }
}


/*
@RestController
@EnableTransactionManagement
@Validated
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/projects/{projectId}/folders")
public class FolderController {
    private static final Logger logger = LoggerFactory.getLogger(FolderController.class);
    //    @Autowired
//    private FolderService folderService ;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    ModelMapper modelMapper;

    //    @GetMapping
//    public ResponseEntity<ApiResponse<List<Folder>>> getAllFolders(@PathVariable Long projectId ){
//        try {
//            List<Folder> folders  = folderService.getAllFolders(projectId);
//            if (folders != null && !folders.isEmpty()) {
//                logger.info("All Folders  successfully retrieved");
//                return ResponseEntity.ok(new ApiResponse<>(true, folders, "All Folders retrieved successfully"));
//            }
//            else {
//                logger.error("No companies found");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, "No Folder found"));
//            }
//        } catch (Exception e) {
//            logger.error("Error occurred while retrieving all folders ", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
//        }
//    }

    @PostMapping("/{parentFolderId}")
    public  ResponseEntity<ApiResponse<?>> createFolder(@PathVariable Long projectId ,@PathVariable Long parentFolderId ,  @Valid @RequestBody Folder  folder ){
        try {
            Optional<Project> projectOptional = projectRepository.findById(projectId);
            if (projectOptional.isPresent()) {
                Project project = projectOptional.get();
                folder.setParentProject(project);
                if (parentFolderId != -1) {
                    Optional<Folder> parentFolderOptional = folderRepository.findById(parentFolderId);
                    if (parentFolderOptional.isPresent()) {
                        Folder parentFolder = parentFolderOptional.get();
                        folder.setParentfolder(parentFolder);
                    } else {
                        throw new ParentNotFoundException("Parent Folder Not Found");
                    }
                }
                        Folder savedFolder = folderRepository.save(folder);
                        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, savedFolder, "Folder Created SuccessFully"));
            } else {
                throw new ParentNotFoundException("Parent Project Not Found");
            }
        }
        catch (ParentNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(false, null, e.getMessage()));
        }
        catch (Exception e) {
            logger.error("Error occurred while retrieving all companies", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }

//
//    @PostMapping("/{parentFolderId}")
//    public ResponseEntity<?> createFolder(
//            @PathVariable Long projectId,
//            @PathVariable Long parentFolderId,
//            @Valid @RequestBody Folder folder
//    ) {
//        try {
//            // Check if this is a root folder (no parent folder)
//            if (parentFolderId != -1) {
//                Optional<Folder> parentFolderOptional = folderRepository.findById(parentFolderId);
//                if (parentFolderOptional.isPresent()) {
//                    Folder parentFolder = parentFolderOptional.get();
//                    if (FolderType.NONLEAF.equals(parentFolder.getFolderType())) {
//                        folder.setParentfolder(parentFolder);
//                    } else {
//                        return ResponseEntity.badRequest().body("ParentFolder is Leaf");
//                    }
//                } else {
//                    return ResponseEntity.notFound().build();
//                }
//            }
//
//            Optional<Project> projectOptional = projectRepository.findById(projectId);
//            if (projectOptional.isPresent()) {
//                Project project = projectOptional.get();
//                folder.setParentProject(project);
//                folderRepository.save(folder);
//                return ResponseEntity.status(HttpStatus.CREATED).body(folder);
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
//        }
//    }

}*/
