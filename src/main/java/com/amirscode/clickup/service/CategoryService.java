package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.CategoryDTO;
import com.amirscode.clickup.payload.CategoryUserDTO;
import org.springframework.http.HttpEntity;

public interface CategoryService {


    HttpEntity<?> addCategory(CategoryDTO categoryDTO);

    HttpEntity<?> editCategory(Long id, CategoryDTO categoryDTO);

    HttpEntity<?> deleteCategory(Long id);

    HttpEntity<?> getCategory(Long id);

    HttpEntity<?> addMember(CategoryUserDTO categoryUserDTO);

    HttpEntity<?> getArchivedCategory(Long id);

    HttpEntity<?> archiveCategory(Long id);

    HttpEntity<?> unArchiveCategory(Long id);
}
