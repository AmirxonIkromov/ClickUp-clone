package com.amirscode.clickup.controller;

import com.amirscode.clickup.entity.CheckList;
import com.amirscode.clickup.payload.CheckListDTO;
import com.amirscode.clickup.service.CheckListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/checkList")

public class CheckListController {

    final CheckListService checkListService;

    @PostMapping
    public HttpEntity<?> addCheckList(@Valid @RequestBody CheckListDTO checkListDTO){
        return checkListService.addCheckList(checkListDTO);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCheckListsByTaskId(@PathVariable UUID id){
        return checkListService.getCheckListsByTaskId(id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCheckList(@PathVariable Long id){
        return checkListService.deleteCheckList(id);
    }

    @PutMapping("{id}/")
    public HttpEntity<?> editCheckListName(@PathVariable Long id, @RequestParam String name ){
        return checkListService.editCheckListName(id, name);
    }
}
