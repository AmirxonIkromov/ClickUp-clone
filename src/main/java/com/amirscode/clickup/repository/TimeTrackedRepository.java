package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.TimeTracked;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TimeTrackedRepository extends JpaRepository<TimeTracked,Long> {

    List<TimeTracked> findAllByTaskId(UUID task_id);
}
