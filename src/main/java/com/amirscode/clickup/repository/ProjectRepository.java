package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllBySpaceIdAndArchived(Long id, boolean b);
}
