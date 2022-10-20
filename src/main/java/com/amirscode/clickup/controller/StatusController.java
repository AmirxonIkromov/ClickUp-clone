package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.StatusDTO;
import com.amirscode.clickup.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/status")

public class StatusController {

    final StatusService statusService;


    @PostMapping
    public HttpEntity<?> addStatus(@Valid @RequestBody StatusDTO statusDTO){
        return statusService.addStatus(statusDTO);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editStatus( @PathVariable Long id, @RequestBody StatusDTO statusDTO){
        return statusService.editStatus(id, statusDTO);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStatus(@PathVariable Long id){
        return statusService.deleteStatus(id);
    }

    @GetMapping()
    public HttpEntity<?> getStatus(@RequestBody StatusDTO statusDTO){
        return statusService.getStatus(statusDTO);
    }
}
