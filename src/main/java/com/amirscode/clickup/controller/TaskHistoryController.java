package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.TaskHistoryDTO;
import com.amirscode.clickup.service.TaskHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/taskHistory")

public class TaskHistoryController {

    final TaskHistoryService taskHistoryService;

    @PostMapping
    public HttpEntity<?> saveHistory(@RequestBody TaskHistoryDTO taskHistoryDTO){
        return taskHistoryService.saveHistory(taskHistoryDTO);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getHistory(@PathVariable UUID id){
        return taskHistoryService.getHistory(id);
    }
}
