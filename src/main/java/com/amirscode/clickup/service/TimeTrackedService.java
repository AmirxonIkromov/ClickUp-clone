package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.TimeTrackedDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface TimeTrackedService {

    HttpEntity<?> addTimeTracked(TimeTrackedDTO timeTrackedDTO);

    HttpEntity<?> getTimeTrackedByTask(UUID id);
}
