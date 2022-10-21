package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.CheckListItemDTO;
import com.amirscode.clickup.service.CheckListItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checkListItem")

public class CheckListItemController {

    final CheckListItemService checkListItemService;

    @PostMapping
    public HttpEntity<?> addCheckListItem(@RequestBody CheckListItemDTO checkListItemDTO){
        return checkListItemService.addCheckListItem(checkListItemDTO);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCheckListItemsByCheckListId(@PathVariable Long id){
        return checkListItemService.getCheckListItemsByCheckListId(id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCheckListItem(@PathVariable Long id){
        return checkListItemService.deleteCheckListItem(id);
    }

    @PutMapping("{id}/")
    public HttpEntity<?> editCheckListItem(@PathVariable Long id, CheckListItemDTO checkListItemDTO){
        return checkListItemService.editCheckLisItem(id, checkListItemDTO);
    }
}
