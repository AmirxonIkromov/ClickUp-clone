package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.CheckListItem;
import com.amirscode.clickup.payload.CheckListItemDTO;
import com.amirscode.clickup.repository.CheckListItemRepository;
import com.amirscode.clickup.repository.CheckListRepository;
import com.amirscode.clickup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckListItemServiceImpl implements CheckListItemService{

    final CheckListItemRepository checkListItemRepository;
    final CheckListRepository checkListRepository;
    final UserRepository userRepository;

    @Override
    public HttpEntity<?> addCheckListItem(CheckListItemDTO checkListItemDTO) {
        var checkList = checkListRepository.findById(checkListItemDTO.getCheckListId()).orElseThrow(ResourceNotFoundException::new);
        var user = userRepository.findById(checkListItemDTO.getAssignedUserId()).orElseThrow(ResourceNotFoundException::new);
        var checkListItem = new CheckListItem(checkListItemDTO.getName(),
                checkList,
                user);
        checkListItemRepository.save(checkListItem);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> getCheckListItemsByCheckListId(Long id) {
        var checkListItemList = checkListItemRepository.findAllByCheckListId(id);

        return ResponseEntity.ok().body(checkListItemList);
    }

    @Override
    public HttpEntity<?> deleteCheckListItem(Long id) {
        var checkListItem = checkListItemRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        checkListItemRepository.delete(checkListItem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public HttpEntity<?> editCheckLisItem(Long id, CheckListItemDTO checkListItemDTO) {
        var checkListItem = checkListItemRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        var user = userRepository.findById(checkListItemDTO.getAssignedUserId()).orElseThrow(ResourceNotFoundException::new);
        checkListItem.setName(checkListItem.getName());
        checkListItem.setCompleted(checkListItemDTO.isCompleted());
        checkListItem.setAssignedUser(user);

        checkListItemRepository.save(checkListItem);
        return ResponseEntity.ok().build();
    }
}
