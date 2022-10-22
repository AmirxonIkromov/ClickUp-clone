package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.TaskHistoryDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface TaskHistoryService {

    HttpEntity<?> saveHistory(TaskHistoryDTO taskHistoryDTO);

    HttpEntity<?> getHistory(UUID id);

}
