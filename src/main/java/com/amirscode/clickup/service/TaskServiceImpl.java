package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.*;
import com.amirscode.clickup.payload.TaskAttachmentDTO;
import com.amirscode.clickup.payload.TaskDTO;
import com.amirscode.clickup.payload.TaskDependencyDTO;
import com.amirscode.clickup.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService{

    final TaskRepository taskRepository;
    final PriorityRepository priorityRepository;
    final CategoryRepository categoryRepository;
    final StatusRepository statusRepository;
    final AttachmentRepository attachmentRepository;
    final TaskDependencyRepository taskDependencyRepository;
    final TagRepository tagRepository;
    final TaskTagRepository taskTagRepository;
    final UserRepository userRepository;
    final TaskUserRepository taskUserRepository;
    final ModelMapper mapper;

    @Override
    public HttpEntity<?> getTasksByStatus(Long id) {
        var taskList = taskRepository.findAllByStatusId(id);
        return ResponseEntity.ok().body(taskList);
    }

    @Override
    public HttpEntity<?> getTasksByCategory(Long id) {
        var taskList = taskRepository.findAllByCategoryId(id);
        return ResponseEntity.ok().body(taskList);
    }

    @Override
    public HttpEntity<?> addTaskDependency(TaskDependencyDTO taskDependencyDTO) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TaskDependency taskDependency = mapper.map(taskDependencyDTO, TaskDependency.class);
        taskDependencyRepository.save(taskDependency);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> addTaskTag(Long tagId, UUID taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(ResourceNotFoundException::new);
        var tag = tagRepository.findById(tagId).orElseThrow(ResourceNotFoundException::new);

        TaskTag taskTag = new TaskTag(task,tag);
        taskTagRepository.save(taskTag);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> assignUser(UUID userId, UUID taskId) {
        var user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        var task = taskRepository.findById(taskId).orElseThrow(ResourceNotFoundException::new);

        TaskUser taskUser = new TaskUser(user, task);
        taskUserRepository.save(taskUser);

        return ResponseEntity.ok().build();
    }

    @Override
    public HttpEntity<?> addAttachmentToTask(TaskAttachmentDTO taskAttachmentDTO) {
        var task = taskRepository.findById(taskAttachmentDTO.getTaskId()).orElseThrow(ResourceNotFoundException::new);
        var attachment = attachmentRepository.findById(taskAttachmentDTO.getAttachmentId()).orElseThrow(ResourceNotFoundException::new);
        new TaskAttachment(task,attachment,taskAttachmentDTO.isPinCoverImage());

        return ResponseEntity.ok("success");
    }

    @Override
    public HttpEntity<?> addTask(TaskDTO taskDTO) {

        var task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStaredDate(taskDTO.getStaredDate());
        task.setDueDate(taskDTO.getDueDate());
        task.setEstimateTime(task.getEstimateTime());
        task.setPriority(taskDTO.getPriorityId() == null ? null : priorityRepository
                .findById(taskDTO.getPriorityId()).orElseThrow(ResourceNotFoundException::new));
        task.setCategory(taskDTO.getCategoryId() == null ? null : categoryRepository
                .findById(taskDTO.getCategoryId()).orElseThrow(ResourceNotFoundException::new));
        task.setStatus(taskDTO.getStatusId() == null ? null : statusRepository
                .findById(taskDTO.getStatusId()).orElseThrow(ResourceNotFoundException::new));

        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @Override
    public HttpEntity<?> editTask(UUID id, TaskDTO taskDTO) {
        var task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        task = mapper.map(taskDTO, Task.class);
        taskRepository.save(task);

        return ResponseEntity.ok("Task edited");
    }

    @Override
    public HttpEntity<?> deleteTask(UUID id) {
        var task = taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        taskRepository.delete(task);

        return ResponseEntity.ok("Task deleted");
    }

    @Override
    public HttpEntity<?> getTasks() {
        return ResponseEntity.ok().body(taskRepository.findAll());
    }

}
