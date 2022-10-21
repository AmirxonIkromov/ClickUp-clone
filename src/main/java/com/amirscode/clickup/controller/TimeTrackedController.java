package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.TimeTrackedDTO;
import com.amirscode.clickup.service.TimeTrackedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/timeTracked")

public class TimeTrackedController {

    final TimeTrackedService timeTrackedService;

    @PostMapping
    public HttpEntity<?> addTimeTracked(@Valid @RequestBody TimeTrackedDTO timeTrackedDTO){
        return timeTrackedService.addTimeTracked(timeTrackedDTO);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getTimeTrackedByTask(@PathVariable UUID id){
        return timeTrackedService.getTimeTrackedByTask(id);
    }
}
