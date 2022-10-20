package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.Status;
import com.amirscode.clickup.payload.StatusDTO;
import com.amirscode.clickup.repository.CategoryRepository;
import com.amirscode.clickup.repository.ProjectRepository;
import com.amirscode.clickup.repository.SpaceRepository;
import com.amirscode.clickup.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class StatusServiceImpl implements StatusService {

    final SpaceRepository spaceRepository;
    final ProjectRepository projectRepository;
    final CategoryRepository categoryRepository;
    final StatusRepository statusRepository;

    @Override
    public ResponseEntity<?> addStatus(StatusDTO statusDTO) {
        var status = new Status();
        status.setName(statusDTO.getName());
        status.setColor(statusDTO.getColor());
        status.setStatusType(statusDTO.getStatusType());
        status.setSpace(statusDTO.getSpaceId() == null ? null : spaceRepository
                .findById(statusDTO.getSpaceId()).orElseThrow(ResourceNotFoundException::new));
        status.setProject(statusDTO.getProjectId() == null ? null : projectRepository
                .findById(statusDTO.getProjectId()).orElseThrow(ResourceNotFoundException::new));
        status.setCategory(statusDTO.getCategoryId() == null ? null : categoryRepository
                .findById(statusDTO.getCategoryId()).orElseThrow(ResourceNotFoundException::new));

        var savedStatus = statusRepository.save(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStatus);
    }

    @Override
    public HttpEntity<?> editStatus(Long id, StatusDTO statusDTO) {
        var status = statusRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        status.setName(statusDTO.getName());
        status.setColor(statusDTO.getColor());
        status.setStatusType(statusDTO.getStatusType());

        statusRepository.save(status);

        return ResponseEntity.ok("Status edited");
    }

    @Override
    public HttpEntity<?> deleteStatus(Long id) {
        var status = statusRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        statusRepository.delete(status);
        return ResponseEntity.ok("Deleted");
    }

    @Override
    public HttpEntity<?> getStatus(StatusDTO statusDTO) {

        if (statusDTO.getSpaceId() != null) {
            var statusList = statusRepository.findAllBySpaceId(statusDTO.getSpaceId());
            return ResponseEntity.ok().body(statusList);
        }
        if (statusDTO.getProjectId() != null) {
            var statusList = statusRepository.findAllByProjectId(statusDTO.getProjectId());
            return ResponseEntity.ok().body(statusList);
        }
        if (statusDTO.getCategoryId() != null) {
            var statusList = statusRepository.findAllByCategoryId(statusDTO.getCategoryId());
            return ResponseEntity.ok().body(statusList);
        }

        return ResponseEntity.badRequest().build();
    }

}
