package com.example.GraplerEnhancemet.service;


import com.example.GraplerEnhancemet.Repository.FolderRepository;
import com.example.GraplerEnhancemet.Repository.ProjectRepository;
import com.example.GraplerEnhancemet.custom_exception.ParentNotFoundException;
import com.example.GraplerEnhancemet.entity.Folder;
import com.example.GraplerEnhancemet.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FolderService {
    private static final Logger logger = LoggerFactory.getLogger(FolderService.class);

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    private FolderRepository folderRepository;

    public List<Folder> getAllFolders(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            List<Folder> folders = folderRepository.findAllByParentProject_Id(projectId);
                logger.info("Retrieved all folders successfully with Project ID : {}",projectId);
                return folders;
        } else {
            logger.error("Parent Project Not Found with ID: {}", projectId);
            throw new ParentNotFoundException("Folders Not Found with Project ID : "+projectId);
        }
    }

    public Folder getFolder(Long folderId) {
        Optional<Folder> folder = folderRepository.findById(folderId);
        if (folder.isPresent()) {
            logger.info("Retrieved folder successfully.");
            return folder.get();
        } else {
            logger.warn("Folder not found with Folder ID: {}", folderId);
            return null;
        }
    }

    public Folder createFolder(Long projectId, Long parentFolderId, Folder folder) {
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
                    logger.warn("Parent Folder Not Found with ID: {}", parentFolderId);
                    throw new ParentNotFoundException("Parent Folder Not Found with ID: " + parentFolderId);
                }
            }
                Folder createdFolder = folderRepository.save(folder);
                logger.info("Top-level Folder created successfully with ID: {}", createdFolder.getID());
                return createdFolder;
        } else {
            logger.error("Parent Project Not Found with ID: {}", projectId);
            throw new ParentNotFoundException("Parent Project Not Found with ID: " + projectId);
        }
    }

    public Folder updateFolder(Long folderId, Folder updatedFolder) {
        Optional<Folder> folder = folderRepository.findById(folderId);
        if (folder.isPresent()) {
            Folder existingFolder = folder.get();
            if (updatedFolder.getName() != null) {
                existingFolder.setName(updatedFolder.getName());
            }
            if (updatedFolder.getFolderType() != null) {
                existingFolder.setFolderType(updatedFolder.getFolderType());
            }
            Folder savedFolder = folderRepository.save(existingFolder);
            logger.info("Folder updated successfully with Folder ID: {}", folderId);
            return savedFolder;
        } else {
            logger.warn("Folder not found with Folder ID: {}", folderId);
            return null;
        }
    }

    public boolean deleteFolder(Long folderId) {
        Optional<Folder> folder = folderRepository.findById(folderId);
        if (folder.isPresent()) {
            folderRepository.delete(folder.get());
            logger.info("Folder deleted successfully with ID: {}", folderId);
            return true;
        } else {
            logger.warn("Folder not found with Folder ID: {}", folderId);
            return false;
        }
    }
}


/*
@Service
@Transactional
public class FolderService {
    private static final Logger logger = LoggerFactory.getLogger(FolderService.class);

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Folder> getAllFolders(Long projectId) {
        try {
            Optional<Project> project = projectRepository.findById(projectId);
            if (project.isPresent()) {
                List<Folder> folders = folderRepository.findAllByParentProject_Id(projectId);
                if(folders != null && !folders.isEmpty()){
                logger.info("Retrieved all folders successfully with Project ID : {}",projectId);
                    return folders;
                } else{
                    logger.error("Folders not Available having Project ID: " + projectId);
                    return null;
                }
                } else {
                throw new ParentNotFoundException("Folders Not Found with Project ID : "+projectId);
            }
        } catch (ParentNotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving folders", e);
            throw e;
        }
    }

    public Folder getFolder(Long projectId, Long folderId) {
        try {
            Optional<Folder> folder = folderRepository.findById(folderId);
            if (folder.isPresent() && folder.get().getParentProject().getId().equals(projectId)) {
                logger.info("Retrieved folder successfully.");
                return folder.get();
            } else {
                logger.error("Folder not found with Project ID: {} and Folder ID: {}",projectId, folderId);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error while getting folder with ID: " + folderId, e);
            throw e;
        }
    }

    public Folder createFolder(Long projectId, Long parentFolderId, Folder folder) {
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
                        throw new ParentNotFoundException("Parent Folder Not Found with ID : "+parentFolderId);
                    }
                }
                return folderRepository.save(folder);
            } else {
                throw new ParentNotFoundException("Parent Project Not Found with ID : "+projectId);
            }
        } catch (ParentNotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error while creating folder", e);
            throw e;
        }
    }

        public Folder updateFolder(Long projectId, Long folderId, Folder updatedFolder) {
        try {
            Optional<Folder> folder = folderRepository.findById(folderId);
            if (folder.isPresent() && folder.get().getParentProject().getId().equals(projectId)) {
                Folder existingFolder = folder.get();
                if(updatedFolder.getName()!= null)
                {
                    existingFolder.setName(updatedFolder.getName());
                }
                if(updatedFolder.getFolderType()!=null)
                {
                    existingFolder.setFolderType(updatedFolder.getFolderType());
                }
                Folder savedFolder = folderRepository.save(existingFolder);
                logger.info("Folder updated successfully with Project ID : {} and Folder ID : {}", projectId,folderId);
                return savedFolder;
            } else {
                throw new ParentNotFoundException("Parent Project Not Found with ID : "+projectId);
            }
        } catch (ParentNotFoundException e) {
            logger.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error while updating folder with Project ID: " + projectId+"and Folder ID: "+folderId, e);
            throw e;
        }
    }

    public boolean deleteFolder(Long projectId, Long folderId) {
        try {
            Optional<Folder> folder = folderRepository.findById(folderId);
            if (folder.isPresent() && folder.get().getParentProject().getId().equals(projectId)) {
                folderRepository.delete(folder.get());
                logger.info("Folder deleted successfully with ID: {}", folderId);
                return true;
            } else {
                logger.error("Folder not found with Project ID: " + projectId+ "and Folder ID:" +folderId);
                return false;
            }
        } catch (Exception e) {
            logger.error("Error while deleting folder with ID: " + folderId, e);
            throw e;
        }
    }
}
*/
