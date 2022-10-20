package com.amirscode.clickup.controller;

import com.amirscode.clickup.entity.SpaceUser;
import com.amirscode.clickup.entity.User;
import com.amirscode.clickup.payload.ApiResponse;
import com.amirscode.clickup.payload.SpaceDTO;
import com.amirscode.clickup.payload.SpaceUserDTO;
import com.amirscode.clickup.security.CurrentUser;
import com.amirscode.clickup.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/space")

public class SpaceController {

    final SpaceService spaceService;

    @PostMapping
    public HttpEntity<?> addSpace(@Valid @RequestBody SpaceDTO spaceDTO, @CurrentUser User user) {
        ApiResponse apiResponse = spaceService.addSpace(spaceDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editSpace(@PathVariable Long id, @RequestBody SpaceDTO spaceDTO) {
        ApiResponse apiResponse = spaceService.editSpace(id, spaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSpace(@PathVariable Long id) {
        ApiResponse apiResponse = spaceService.deleteSpace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getSpace(@PathVariable Long id) {
        return spaceService.getSpace(id);
    }

    @PostMapping
    public HttpEntity<?> addMember(@RequestBody SpaceUserDTO spaceUserDTO) {
        return spaceService.addMember(spaceUserDTO);
    }

    @GetMapping("/allArchive{id}")
    public HttpEntity<?> getArchivedSpace(@PathVariable Long id){
        return spaceService.getArchivedSpace(id);
    }

    @PutMapping("/archive/{id}")
    public HttpEntity<?> ArchiveSpace(@PathVariable Long id){
        return spaceService.archiveSpace(id);
    }

    @PutMapping("unArchive/{id}")
    public HttpEntity<?> UnArchiveSpace(@PathVariable Long id){
        return spaceService.unArchiveSpace(id);
    }
}
