package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.Category;
import com.amirscode.clickup.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByProjectIdAndArchived(Long id, boolean archive);
}
