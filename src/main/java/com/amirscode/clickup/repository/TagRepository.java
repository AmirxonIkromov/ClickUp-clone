package com.amirscode.clickup.repository;


import com.amirscode.clickup.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
