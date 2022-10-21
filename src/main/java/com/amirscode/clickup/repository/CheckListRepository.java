package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface CheckListRepository extends JpaRepository<CheckList,Long> {

    List<CheckList> findAllByTaskId(UUID task_id);
}
