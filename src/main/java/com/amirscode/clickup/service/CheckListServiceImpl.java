package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.CheckList;
import com.amirscode.clickup.payload.CheckListDTO;
import com.amirscode.clickup.repository.CheckListRepository;
import com.amirscode.clickup.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service

public class CheckListServiceImpl implements CheckListService{

    final CheckListRepository checkListRepository;
    final TaskRepository taskRepository;


    @Override
    public HttpEntity<?> addCheckList(CheckListDTO checkListDTO) {
        var task = taskRepository.findById(checkListDTO.getTaskId())
                .orElseThrow(ResourceNotFoundException::new);
        CheckList checkList = new CheckList(checkListDTO.getName(),task);

        checkListRepository.save(checkList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> getCheckListsByTaskId(UUID id) {
        return ResponseEntity.ok().body(checkListRepository.findAllByTaskId(id));
    }

    @Override
    public HttpEntity<?> deleteCheckList(Long id) {
        var checkList = checkListRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        checkListRepository.delete(checkList);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public HttpEntity<?> editCheckListName(Long id, String name) {
        var checkList = checkListRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        checkList.setName(name);
        checkListRepository.save(checkList);

        return ResponseEntity.ok().build();
    }
}
