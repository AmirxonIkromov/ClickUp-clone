package com.amirscode.clickup.repository;


import com.amirscode.clickup.entity.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {
}
