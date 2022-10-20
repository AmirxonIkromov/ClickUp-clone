package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.TagDTO;
import com.amirscode.clickup.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tag")

public class TagController {

    final TagService tagService;

    @PostMapping
    public HttpEntity<?> addTag(@RequestBody TagDTO tagDTO){
        return tagService.addTag(tagDTO);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editTag(@PathVariable Long id, @Valid @RequestBody TagDTO tagDTO){
        return tagService.editTag(id, tagDTO);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }
}
