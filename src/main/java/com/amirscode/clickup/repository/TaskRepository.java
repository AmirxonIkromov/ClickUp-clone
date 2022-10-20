package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {


}
