package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.CheckListItemDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface CheckListItemService {

    HttpEntity<?> addCheckListItem(CheckListItemDTO checkListItemDTO);

    HttpEntity<?> getCheckListItemsByCheckListId(Long id);

    HttpEntity<?> deleteCheckListItem(Long id);

    HttpEntity<?> editCheckLisItem(Long id, CheckListItemDTO checkListItemDTO);
}
