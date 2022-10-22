package com.amirscode.clickup.repository;


import com.amirscode.clickup.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, UUID> {

    List<TaskHistory> findAllByTaskId(UUID task_id);
}
