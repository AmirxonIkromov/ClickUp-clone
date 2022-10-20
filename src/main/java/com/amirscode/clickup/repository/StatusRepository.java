package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findAllBySpaceId(Long space_id);

    List<Status> findAllByProjectId(Long project_id);

    List<Status> findAllByCategoryId(Long category_id);
}
