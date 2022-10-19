package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.ProjectDTO;
import com.amirscode.clickup.payload.ProjectUserDTO;
import com.amirscode.clickup.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/project")

public class ProjectController {

    final ProjectService projectService;


    @PostMapping
    public HttpEntity<?> addProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.addProject(projectDTO);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectService.editProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProject(@PathVariable Long id){
        return projectService.getProject(id);
    }

    @PostMapping
    public HttpEntity<?> addMember(@RequestBody ProjectUserDTO projectUserDTO) {
        return projectService.addMember(projectUserDTO);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getArchivedProject(@PathVariable Long id){
        return projectService.getArchivedProject(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> ArchiveProject(@PathVariable Long id){
        return projectService.archiveProject(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> UnArchiveProject(@PathVariable Long id){
        return projectService.unArchiveProject(id);
    }
}
