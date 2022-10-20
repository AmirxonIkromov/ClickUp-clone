package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.Project;
import com.amirscode.clickup.entity.ProjectUser;
import com.amirscode.clickup.payload.ProjectDTO;
import com.amirscode.clickup.payload.ProjectUserDTO;
import com.amirscode.clickup.repository.ProjectRepository;
import com.amirscode.clickup.repository.ProjectUserRepository;
import com.amirscode.clickup.repository.SpaceRepository;
import com.amirscode.clickup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    final SpaceRepository spaceRepository;
    final ProjectRepository projectRepository;
    final UserRepository userRepository;
    final ProjectUserRepository projectUserRepository;

    @Override
    public ResponseEntity<?> addProject(ProjectDTO projectDTO) {
        var space = spaceRepository.findById(projectDTO.getSpaceId()).orElseThrow(IllegalAccessError::new);

        var project = new Project();
        project.setName(projectDTO.getName());
        project.setColor(projectDTO.getColor());
        project.setAccessType(projectDTO.getAccessType());
        project.setSpace(space);

        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @Override
    public ResponseEntity<?> editProject(Long id, ProjectDTO projectDTO) {
        var space = spaceRepository.findById(projectDTO.getSpaceId()).orElseThrow(IllegalAccessError::new);
        var project = projectRepository.findById(id).orElseThrow(IllegalAccessError::new);

        project.setName(projectDTO.getName());
        project.setColor(projectDTO.getColor());
        project.setAccessType(projectDTO.getAccessType());
        project.setSpace(space);

        projectRepository.save(project);

        return ResponseEntity.status(HttpStatus.OK).body("Project edited");
    }

    @Override
    public ResponseEntity<?> deleteProject(Long id) {
        var project = projectRepository.findById(id).orElseThrow(IllegalAccessError::new);
        projectRepository.delete(project);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @Override
    public ResponseEntity<?> addMember(ProjectUserDTO projectUserDTO) {

        var project = projectRepository.findById(projectUserDTO.getProject()).orElseThrow(IllegalAccessError::new);
        var user = userRepository.findById(projectUserDTO.getUser()).orElseThrow(IllegalStateException::new);

        var projectUser = new ProjectUser();
        projectUser.setProject(project);
        projectUser.setUser(user);
        projectUser.setPermission(projectUserDTO.getPermissionName());
        projectUserRepository.save(projectUser);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> getArchivedProject(Long id) {
        spaceRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return ResponseEntity.ok().body(projectRepository.findAllBySpaceIdAndArchived(id, true));
    }

    @Override
    public ResponseEntity<?> archiveProject(Long id) {
        var project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        project.setArchived(true);
        projectRepository.save(project);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> unArchiveProject(Long id) {
        var project = projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        project.setArchived(false);
        projectRepository.save(project);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> getProject(Long id) {
        spaceRepository.findById(id).orElseThrow(IllegalStateException::new);
        var projectList = projectRepository.findAllBySpaceIdAndArchived(id, false);

        return ResponseEntity.ok(projectList);
    }
}
