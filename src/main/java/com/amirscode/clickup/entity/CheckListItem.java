package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CheckListItem extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    private boolean completed;

    @ManyToOne
    private CheckList checkList;

    @OneToOne
    private User assignedUser;

    public CheckListItem(String name, CheckList checkList, User assignedUser) {
        this.name = name;
        this.checkList = checkList;
        this.assignedUser = assignedUser;
    }
}