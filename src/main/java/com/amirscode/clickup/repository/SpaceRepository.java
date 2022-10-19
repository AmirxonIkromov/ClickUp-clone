package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space,Long> {

    boolean existsByWorkspaceIdAndName(Long workspace_id, String name);

    List<Space> findAllByWorkspaceIdAndArchived(Long workspace_id, boolean archived);

}
