package com.amirscode.clickup.service;


import com.amirscode.clickup.payload.ProjectDTO;
import com.amirscode.clickup.payload.ProjectUserDTO;
import org.springframework.http.ResponseEntity;

public interface ProjectService {


    ResponseEntity<?> addProject(ProjectDTO projectDTO);

    ResponseEntity<?> editProject(Long id, ProjectDTO projectDTO);

    ResponseEntity<?> deleteProject(Long id);

    ResponseEntity<?> addMember(ProjectUserDTO projectUserDTO);

    ResponseEntity<?> getArchivedProject(Long id);

    ResponseEntity<?> archiveProject(Long id);

    ResponseEntity<?> unArchiveProject(Long id);

    ResponseEntity<?> getProject(Long id);
}
