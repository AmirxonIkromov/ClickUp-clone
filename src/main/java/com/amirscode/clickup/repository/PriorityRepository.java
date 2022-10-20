package com.amirscode.clickup.repository;


import com.amirscode.clickup.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
