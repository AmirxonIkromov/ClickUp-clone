package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.Category;
import com.amirscode.clickup.entity.CategoryUser;
import com.amirscode.clickup.payload.CategoryDTO;
import com.amirscode.clickup.payload.CategoryUserDTO;
import com.amirscode.clickup.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    final ProjectRepository projectRepository;
    final UserRepository userRepository;
    final CategoryRepository categoryRepository;
    final CategoryUserRepository categoryUserRepository;

    @Override
    public ResponseEntity<?> addCategory(CategoryDTO categoryDTO) {
        var project = projectRepository.findById(categoryDTO.getProjectId()).orElseThrow(IllegalAccessError::new);

        var category = new Category();
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setAccessType(categoryDTO.getAccessType());
        category.setProject(project);

        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    @Override
    public ResponseEntity<?> editCategory(Long id, CategoryDTO categoryDTO) {
        var project = projectRepository.findById(categoryDTO.getProjectId()).orElseThrow(IllegalAccessError::new);
        var category = categoryRepository.findById(id).orElseThrow(IllegalAccessError::new);

        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setAccessType(categoryDTO.getAccessType());
        category.setProject(project);

        categoryRepository.save(category);

        return ResponseEntity.status(HttpStatus.OK).body("Category edited");
    }

    @Override
    public ResponseEntity<?> deleteCategory(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(IllegalAccessError::new);
        categoryRepository.delete(category);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @Override
    public ResponseEntity<?> addMember(CategoryUserDTO categoryUserDTO) {

        var category = categoryRepository.findById(categoryUserDTO.getCategoryId()).orElseThrow(IllegalAccessError::new);
        var user = userRepository.findById(categoryUserDTO.getUserId()).orElseThrow(IllegalStateException::new);

        var categoryUser = new CategoryUser();
        categoryUser.setCategory(category);
        categoryUser.setUser(user);
        categoryUser.setPermissionName(categoryUserDTO.getPermissionName());
        categoryUserRepository.save(categoryUser);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> getArchivedCategory(Long id) {
        projectRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return ResponseEntity.ok().body(categoryRepository.findAllByProjectIdAndArchived(id, true));
    }

    @Override
    public ResponseEntity<?> archiveCategory(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(IllegalStateException::new);
        category.setArchived(true);
        categoryRepository.save(category);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> unArchiveCategory(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(IllegalStateException::new);
        category.setArchived(false);
        categoryRepository.save(category);

        return ResponseEntity.ok().body("success");
    }

    @Override
    public ResponseEntity<?> getCategory(Long id) {
        projectRepository.findById(id).orElseThrow(IllegalStateException::new);
        var categoryList = categoryRepository.findAllByProjectIdAndArchived(id, false);

        return ResponseEntity.ok().body(categoryList);
    }
}
