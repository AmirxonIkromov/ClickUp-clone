package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.*;
import com.amirscode.clickup.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/task")

public class TaskController {

    final TaskService taskService;

    @GetMapping("/tasksByStatus/{id}")
    public HttpEntity<?> getTasksByStatus(@PathVariable Long id){
        return taskService.getTasksByStatus(id);
    }

    @GetMapping("/tasksByCategory/{id}")
    public HttpEntity<?> getTasksByCategory(@PathVariable Long id){
        return taskService.getTasksByCategory(id);
    }

    @PostMapping("/taskTag")
    public HttpEntity<?> addTaskTag(@RequestParam  Long tagId, UUID taskId){
        return taskService.addTaskTag(tagId, taskId);
    }

    @PostMapping("/taskDependency")
    public HttpEntity<?> addTaskDependency(@Valid @RequestBody TaskDependencyDTO taskDependencyDTO){
        return taskService.addTaskDependency(taskDependencyDTO);
    }

    @PostMapping("/assignUser")
    public HttpEntity<?> assignUser(@RequestParam UUID userId, UUID taskId) {
        return taskService.assignUser(userId, taskId);
    }

    @PutMapping("/attachmentToTask")
    public HttpEntity<?> addAttachmentToTask(@Valid @RequestBody TaskAttachmentDTO taskAttachmentDTO){
        return taskService.addAttachmentToTask(taskAttachmentDTO);
    }

    @PostMapping
    public HttpEntity<?> addTask(@Valid @RequestBody TaskDTO taskDTO){
        return taskService.addTask(taskDTO);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO){
        return taskService.editTask(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTask(@PathVariable UUID id){
        return taskService.deleteTask(id);
    }

}
