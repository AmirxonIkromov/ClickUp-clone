package com.amirscode.clickup.service;


import com.amirscode.clickup.entity.User;
import com.amirscode.clickup.payload.ApiResponse;
import com.amirscode.clickup.payload.MemberDTO;
import com.amirscode.clickup.payload.WorkspaceDTO;

import java.util.List;
import java.util.UUID;


public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user);

    ApiResponse editWorkspace(Long id,WorkspaceDTO workspaceDTO);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO);

    ApiResponse joinToWorkspace(Long id, User user);

    List<WorkspaceDTO> getMyWorkspaces(User user);
}
