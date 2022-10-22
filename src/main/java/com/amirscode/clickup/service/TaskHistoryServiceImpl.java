package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.TaskHistory;
import com.amirscode.clickup.payload.TaskHistoryDTO;
import com.amirscode.clickup.repository.TaskHistoryRepository;
import com.amirscode.clickup.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service

public class TaskHistoryServiceImpl implements TaskHistoryService{

    final TaskRepository taskRepository;
    final TaskHistoryRepository taskHistoryRepository;

    @Override
    public HttpEntity<?> saveHistory(TaskHistoryDTO taskHistoryDTO) {
        var task = taskRepository.findById(taskHistoryDTO.getTaskId()).orElseThrow();
        TaskHistory taskHistory = new TaskHistory();
        taskHistory.setTask(task);
        taskHistory.setField(taskHistoryDTO.getField());
        taskHistory.setBefore(taskHistoryDTO.getBefore());
        taskHistory.setAfter(taskHistoryDTO.getAfter());
        taskHistory.setDate(taskHistoryDTO.getDate());
        taskHistory.setData(taskHistoryDTO.getData());

        taskHistoryRepository.save(taskHistory);
        return ResponseEntity.ok().build();
    }

    @Override
    public HttpEntity<?> getHistory(UUID id) {
        var taskHistoryList = taskHistoryRepository.findAllByTaskId(id);

        return ResponseEntity.ok().body(taskHistoryList);
    }
}
