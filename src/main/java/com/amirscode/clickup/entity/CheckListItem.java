package com.amirscode.clickup.entity;

import com.amirscode.clickup.template.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class CheckListItem extends AbsLongEntity {

    private String name;

    private boolean resolved;

    @ManyToOne
    private CheckList checkList;

    @OneToOne
    private User assignedUser;
}