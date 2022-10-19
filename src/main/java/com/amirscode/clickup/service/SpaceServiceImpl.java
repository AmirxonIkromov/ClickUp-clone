package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.*;
import com.amirscode.clickup.payload.ApiResponse;
import com.amirscode.clickup.payload.SpaceDTO;
import com.amirscode.clickup.payload.SpaceUserDTO;
import com.amirscode.clickup.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    final SpaceRepository spaceRepository;
    final AttachmentRepository attachmentRepository;
    final WorkspaceRepository workspaceRepository;
    final IconRepository iconRepository;
    final SpaceUserRepository spaceUserRepository;
    final UserRepository userRepository;
    final SpaceClickAppsRepository spaceClickAppsRepository;
    final ClickAppsRepository clickAppsRepository;
    final ViewRepository viewRepository;
    final SpaceViewRepository spaceViewRepository;

    @Override
    public ApiResponse addSpace(SpaceDTO spaceDTO, User user) {
        if (spaceRepository.existsByWorkspaceIdAndName(spaceDTO.getWorkspaceId(), spaceDTO.getName()))
            return new ApiResponse("You already have a space with that name", false);
        Space space = new Space();
        space.setName(spaceDTO.getName());
        space.setColor(spaceDTO.getColor());
        space.setAccessType(spaceDTO.getAccessType());
        space.setOwner(user);
        space.setWorkspace(workspaceRepository.findById(spaceDTO.getWorkspaceId()).get());
        space.setIcon(spaceDTO.getIconId() == null ? null : iconRepository
                .findById(spaceDTO.getIconId())
                .orElseThrow(() -> new ResourceNotFoundException("icon not found")));
        space.setAvatar(spaceDTO.getAvatarId() == null ? null : attachmentRepository
                .findById(spaceDTO.getAvatarId())
                .orElseThrow(() -> new ResourceNotFoundException("attachment not found")));

        var savedSpace = spaceRepository.save(space);

        spaceUserRepository.save(new SpaceUser(space, userRepository.findById(spaceDTO.getAssignedUser()).get()));

        SpaceClickApps spaceClickApps = new SpaceClickApps();
        spaceClickApps.setClickApps(clickAppsRepository.findAllById(spaceDTO.getClickAppsId()));
        spaceClickApps.setSpace(savedSpace);

        spaceClickAppsRepository.save(spaceClickApps);

        SpaceView spaceView = new SpaceView();
        spaceView.setView(viewRepository.findAllById(spaceDTO.getViewId()));
        spaceView.setSpace(savedSpace);
        spaceViewRepository.save(spaceView);

        return new ApiResponse("Space created", true);
    }

    @Override
    public ApiResponse editSpace(Long id, SpaceDTO spaceDTO) {
        Space space = spaceRepository.findById(id).orElseThrow(() -> new IllegalStateException("space id not found"));

        space.setName(spaceDTO.getName());
        space.setColor(spaceDTO.getColor());
        space.setAccessType(spaceDTO.getAccessType());
        space.setIcon(spaceDTO.getIconId() == null ? null : iconRepository
                .findById(spaceDTO.getIconId())
                .orElseThrow(() -> new ResourceNotFoundException("icon not found")));
        space.setAvatar(spaceDTO.getAvatarId() == null ? null : attachmentRepository
                .findById(spaceDTO.getAvatarId())
                .orElseThrow(() -> new ResourceNotFoundException("attachment not found")));

        return new ApiResponse("Space edited", true);
    }

    @Override
    public ApiResponse deleteSpace(Long id) {

        try {
            spaceRepository.deleteById(id);
            return new ApiResponse("Space deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    @Override
    public ResponseEntity<?> getSpace(Long id) {
        workspaceRepository.findById(id).orElseThrow(IllegalStateException::new);
        var allByWorkspace = spaceRepository.findAllByWorkspaceIdAndArchived(id, false);

        return ResponseEntity.ok().body(allByWorkspace);
    }

    @Override
    public ResponseEntity<?> addMember(SpaceUserDTO spaceUserDTO) {

        var space = spaceRepository.findById(spaceUserDTO.getSpaceId()).orElseThrow(IllegalAccessError::new);
        var user = userRepository.findById(spaceUserDTO.getMemberId()).orElseThrow(IllegalStateException::new);

        var spaceUser = new SpaceUser();
        spaceUser.setMember(user);
        spaceUser.setSpace(space);
        spaceUserRepository.save(spaceUser);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> getArchivedSpace(Long id) {
        workspaceRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return ResponseEntity.ok().body(spaceRepository.findAllByWorkspaceIdAndArchived(id, true));
    }

    @Override
    public ResponseEntity<?> archiveSpace(Long id) {
        var space = spaceRepository.findById(id).orElseThrow(IllegalStateException::new);
        space.setArchived(true);
        spaceRepository.save(space);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> unArchiveSpace(Long id) {
        var space = spaceRepository.findById(id).orElseThrow(IllegalStateException::new);
        space.setArchived(false);
        spaceRepository.save(space);

        return ResponseEntity.ok().body("success");
    }

}
