package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.CheckListDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface CheckListService {

    HttpEntity<?> addCheckList(CheckListDTO checkListDTO);

    HttpEntity<?> getCheckListsByTaskId(UUID id);

    HttpEntity<?> deleteCheckList(Long id);

    HttpEntity<?> editCheckListName(Long id, String name);
}
