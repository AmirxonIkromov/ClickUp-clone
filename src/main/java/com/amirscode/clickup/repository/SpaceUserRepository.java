package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.SpaceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceUserRepository extends JpaRepository<SpaceUser,Long> {
}
