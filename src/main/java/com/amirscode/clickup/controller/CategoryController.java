package com.amirscode.clickup.controller;

import com.amirscode.clickup.payload.CategoryDTO;
import com.amirscode.clickup.payload.CategoryUserDTO;
import com.amirscode.clickup.payload.ProjectUserDTO;
import com.amirscode.clickup.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")

public class CategoryController {

    final CategoryService categoryService;


    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.addCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.editProject(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping("/member")
    public HttpEntity<?> addMember(@RequestBody CategoryUserDTO categoryUserDTO) {
        return categoryService.addMember(categoryUserDTO);
    }

    @GetMapping("/allArchive/{id}")
    public HttpEntity<?> getArchivedCategory(@PathVariable Long id){
        return categoryService.getArchivedCategory(id);
    }

    @PutMapping("/archive/{id}")
    public HttpEntity<?> ArchiveCategory(@PathVariable Long id){
        return categoryService.archiveCategory(id);
    }

    @PutMapping("/unArchive/{id}")
    public HttpEntity<?> UnArchiveCategory(@PathVariable Long id){
        return categoryService.unArchiveCategory(id);
    }
}
