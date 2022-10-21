package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.TimeTracked;
import com.amirscode.clickup.payload.TimeTrackedDTO;
import com.amirscode.clickup.repository.TaskRepository;
import com.amirscode.clickup.repository.TimeTrackedRepository;
import com.amirscode.clickup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service

public class TimeTrackedServiceImpl implements TimeTrackedService {

    final TimeTrackedRepository timeTrackedRepository;
    final TaskRepository taskRepository;
    final UserRepository userRepository;

    @Override
    public HttpEntity<?> addTimeTracked(TimeTrackedDTO timeTrackedDTO) {
        var task = taskRepository.findById(timeTrackedDTO.getTaskId()).orElseThrow(ResourceNotFoundException::new);
        var timeTracked = new TimeTracked(task, timeTrackedDTO.getStartedAt(), timeTrackedDTO.getStoppedAt());
        timeTrackedRepository.save(timeTracked);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> getTimeTrackedByTask(UUID id) {
        var timeTrackedList = timeTrackedRepository.findAllByTaskId(id);

        return ResponseEntity.ok().body(timeTrackedList);
    }
}
