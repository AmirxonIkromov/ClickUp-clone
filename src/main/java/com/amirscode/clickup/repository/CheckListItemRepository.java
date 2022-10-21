package com.amirscode.clickup.repository;

import com.amirscode.clickup.entity.CheckListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckListItemRepository extends JpaRepository<CheckListItem,Long> {

    List<CheckListItem> findAllByCheckListId(Long checkList_id);
}
