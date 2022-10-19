package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.User;
import com.amirscode.clickup.payload.ApiResponse;
import com.amirscode.clickup.payload.SpaceDTO;
import com.amirscode.clickup.payload.SpaceUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface SpaceService {


    ApiResponse addSpace(SpaceDTO spaceDTO, User user);

    ApiResponse editSpace(Long id, SpaceDTO spaceDTO);

    ApiResponse deleteSpace(Long id);

    ResponseEntity<?> getSpace(Long id);

    ResponseEntity<?> addMember(SpaceUserDTO spaceUserDTO);

    ResponseEntity<?> getArchivedSpace(Long id);

    ResponseEntity<?> archiveSpace(Long id);

    ResponseEntity<?> unArchiveSpace(Long id);

}
