package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.TagDTO;
import com.amirscode.clickup.payload.TaskAttachmentDTO;
import com.amirscode.clickup.payload.TaskDTO;
import com.amirscode.clickup.payload.TaskDependencyDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface TaskService {

    HttpEntity<?> addTask(TaskDTO taskDTO);
    HttpEntity<?> editTask(UUID id, TaskDTO taskDTO);

    HttpEntity<?> deleteTask(UUID id);

    HttpEntity<?> getTasks();

    HttpEntity<?> addAttachmentToTask(TaskAttachmentDTO taskAttachmentDTO);

    HttpEntity<?> addTaskDependency(TaskDependencyDTO taskDependencyDTO);

    HttpEntity<?> addTaskTag(Long tagId, UUID taskId);

    HttpEntity<?> assignUser(UUID userId, UUID taskId);

    HttpEntity<?> getTasksByStatus(Long id);

    HttpEntity<?> getTasksByCategory(Long id);
}
