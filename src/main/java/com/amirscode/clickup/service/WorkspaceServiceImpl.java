package com.amirscode.clickup.service;

import com.amirscode.clickup.config.SecurityConfig;
import com.amirscode.clickup.entity.*;
import com.amirscode.clickup.enums.AddType;
import com.amirscode.clickup.enums.WorkspacePermissionName;
import com.amirscode.clickup.enums.WorkspaceRoleName;
import com.amirscode.clickup.payload.ApiResponse;
import com.amirscode.clickup.payload.MemberDTO;
import com.amirscode.clickup.payload.WorkspaceDTO;
import com.amirscode.clickup.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
   final WorkspaceRepository workspaceRepository;
   final SecurityConfig securityConfig;
   final AttachmentRepository attachmentRepository;
   final WorkspaceUserRepository workspaceUserRepository;
   final WorkspaceRoleRepository workspaceRoleRepository;
   final WorkspacePermissionRepository workspacePermissionRepository;
   final UserRepository userRepository;

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user) {
        // OPEN WORKSPACE
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDTO.getName()))
            return new ApiResponse("You already have a workspace with that name", false);
        Workspace workspace = new Workspace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment"))
        );
        workspaceRepository.save(workspace);

        // OPEN WORKSPACE ROLE

        WorkspaceRole ownerRole = addRole(workspace);

        WorkspaceRole adminRole = adminRole(workspace);

        WorkspaceRole memberRole = memberRole(workspace);

        WorkspaceRole guestRole = guestRole(workspace);


        addPermission(ownerRole,adminRole,memberRole,guestRole);

        // OPEN WORKSPACE USER

        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));

        return new ApiResponse("Workspace created", true);
    }



    @Override
    public ApiResponse editWorkspace(Long id,WorkspaceDTO workspaceDTO) {

        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new IllegalStateException("workspace id not found"));

        workspace.setName(workspaceDTO.getName());
        workspace.setColor(workspaceDTO.getColor());
        workspace.setOwner(securityConfig.getCurrentUser());
        workspace.setAvatar(workspaceDTO.getAvatarId() == null ? null : attachmentRepository.findById(workspaceDTO.getAvatarId()).orElseThrow(() -> new ResourceNotFoundException("attachment")));

        workspaceRepository.save(workspace);

        return new ApiResponse("Workspace edited", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId) {

        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new IllegalStateException("workspace id not found"));

        WorkspaceRole ownerRole = addRole(workspace);

        WorkspaceRole adminRole = adminRole(workspace);

        WorkspaceRole memberRole = memberRole(workspace);

        WorkspaceRole guestRole = guestRole(workspace);

        addPermission(ownerRole,adminRole,memberRole,guestRole);

        User user = userRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User does not exist"));

        workspaceUserRepository.save(new WorkspaceUser(
                workspace,
                user,
                ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())

        ));

        return new ApiResponse("Owner changed", true);
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("Workspace deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) {
        if (memberDTO.getAddType().equals(AddType.ADD)) {
            WorkspaceUser workspaceUser = new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            );
            workspaceUserRepository.save(workspaceUser);

            //TODO, SEND INVITE MASSAGE TO EMAIL
        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkspaceUser::new);
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")));
            workspaceUserRepository.save(workspaceUser);
        } else if (memberDTO.getAddType().equals(AddType.REMOVE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, memberDTO.getId());
        }
        return new ApiResponse("SUCCESS", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {
        Optional<WorkspaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkspaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    @Override
    public ApiResponse getAllWorkspace() {

        List<Workspace> all = workspaceRepository.findAll();
        return new ApiResponse("success", true, all);
    }


    public WorkspaceRole addRole(Workspace workspace){
        return workspaceRoleRepository.save(new WorkspaceRole(
                workspace,
                WorkspaceRoleName.ROLE_OWNER.name(),
                null));
    }

    public WorkspaceRole adminRole(Workspace workspace){
        return workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_ADMIN.name(), null));
    }
    public WorkspaceRole memberRole(Workspace workspace){
        return workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_MEMBER.name(), null));
    }
    public WorkspaceRole guestRole(Workspace workspace){
        return workspaceRoleRepository.save(new WorkspaceRole(workspace, WorkspaceRoleName.ROLE_GUEST.name(), null));
    }


    private void addPermission(WorkspaceRole ownerRole, WorkspaceRole adminRole, WorkspaceRole memberRole, WorkspaceRole guestRole) {
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {
            WorkspacePermission workspacePermission = new WorkspacePermission(
                    ownerRole,
                    workspacePermissionName);
            workspacePermissions.add(workspacePermission);
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(
                        adminRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(
                        memberRole,
                        workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(
                        guestRole,
                        workspacePermissionName));
            }

        }
        workspacePermissionRepository.saveAll(workspacePermissions);
    }
}
