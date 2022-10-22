package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.Comment;
import com.amirscode.clickup.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByStatusId(Long status_id);

    List<Task> findAllByCategoryId(Long category_id);

}
